package song.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import song.model.NewsDetail;
import song.model.NewsItem;
import song.repository.NewsDetailRepository;
import song.repository.NewsItemRepository;
import song.utils.SaeUploadUtils;
import song.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Song on 2015/6/13.
 */
@Controller
@RequestMapping("/news")
@SessionAttributes("newsList")
public class NewsController {

    Logger logger = Logger.getLogger(NewsController.class);

    @Autowired
    private NewsItemRepository newsItemRepository;
    @Autowired
    private NewsDetailRepository newsDetailRepository;

    /*
        跳转到添加新闻页面
  */
    @RequestMapping(value = "/addNewsPage",method = RequestMethod.GET)
    public String toAddNewsPage(){

        return "add_news";
    }
    /*
        添加,更新新闻
        2015-6-18 22:49:22
     */
    @RequestMapping(value="/addNews",method = RequestMethod.POST)
    public String addNews(Long id ,String title,String content,String fromPublisher,
                          @RequestParam(value = "publishTime",required=false)String publishTime,int beenRead,  Model model){
        Date time = null;
        if(!StringUtils.isEmpty(title,content,fromPublisher)){
            if(publishTime==null){
                time = new Date();
            }else {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                try {
                    time = sdf.parse(publishTime);
                } catch (ParseException e) {
                    e.printStackTrace();
                    logger.error("Parse Date String Error!Check publishTime!");
                }
            }
            if(id==null){//ID为空，说明为添加新闻
                NewsDetail newsDetail = new NewsDetail(title, content, 0);
                newsDetailRepository.save(newsDetail);
                NewsItem item = new NewsItem(id, title, null, null, null, time, 0, null, "DzNews", newsDetail);
                newsItemRepository.save(item);//save news
            }else {//ID不为空，说明为更新新闻
                NewsItem item = newsItemRepository.findOne(id);
                item.setTitle(title);
                item.getNewsDetail().setContent(content);
                item.setFromPublisher(fromPublisher);
                newsItemRepository.save(item);
            }
        }
        model.asMap().clear();
        return "add_news";
    }

    /*
        处理上传的图片
     */
    @RequestMapping(value = "/uploadImg",method = RequestMethod.POST)
    @ResponseBody
    public ModelAndView uploadImgForNews(@RequestParam("upload_file")MultipartFile file,HttpServletRequest request){
        Date uploadTime =new Date();
        String file_name ="";
        String imgUrl ="";
        Map response = new HashMap();
        if(!file.isEmpty()){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
//            file_name = MD5Encoder.encode(sdf.format(new Date()).getBytes());
            file_name = sdf.format(uploadTime);
            String file_type = file.getContentType();
            switch (file_type){
                case "image/jpeg":
                    file_name+=".jpg";
                    break;
                case "image/bmp":
                    file_name+=".bmp";
                    break;
                case "image/png":
                    file_name+=".png";
                    break;
                default:
                    response.put("success", false);
                    if (file.getSize()>1024*1024) {
                        response.put("msg", "上传失败,请检查图片大小.");
                    }else {
                        response.put("msg", "上传失败,请检查图片.");
                    }
                    response.put("file_path", null);
                    return new ModelAndView(new MappingJackson2JsonView(),response);
            }
            try {
                /*
                    将MultipartFile转换成File
                 */
                sdf = new SimpleDateFormat("yyyyMMddHH");
                //将图片上传到新浪云
                imgUrl = SaeUploadUtils.upload("songnews","news/"+sdf.format(uploadTime)+"/"
                        +file_name,MultipartFileToFile(file));

            } catch (IOException e) {
                logger.warn("File Store Failed !",new IOException(e));
                e.printStackTrace();
                response.put("success", false);
                response.put("msg", "上传失败,请检查图片.");
                response.put("file_path", null);
                return new ModelAndView(new MappingJackson2JsonView(),response);
            }
        }
        response.put("success", true);
        response.put("msg", "上传成功.");
        response.put("file_path", imgUrl);
        return new ModelAndView(new MappingJackson2JsonView(),response);

    }
    /*
        将MultipartFile转换成File
    */
    public File MultipartFileToFile(MultipartFile multipart) throws IOException {
        File tmpFile = new File(System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") +
                multipart.getOriginalFilename());
        //也可以采用File.createTempFile()
        multipart.transferTo(tmpFile);
        return tmpFile;
    }

    /*
        展示新闻细节页面
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public String showNewsDetail(@PathVariable String id ,Model model){
        NewsItem news  = newsItemRepository.findOne(Long.parseLong(id));
        NewsDetail detail  = news.getNewsDetail();
        model.addAttribute("news",detail);
        news.setBeenRead(news.getBeenRead()+1);
        newsItemRepository.save(news);//更新数据
        return "news_detail";
    }

    /*
        删除新闻
     */
    @RequestMapping(value = "/delete/{id}",method = RequestMethod.GET)
    public String deleteNews(@PathVariable("id") String id ,Model model,HttpServletRequest request){
        newsItemRepository.delete(Long.parseLong(id));
        return "redirect:../";
    }

    /*
        刷新模型
        跳转到后台首页
     */
    @RequestMapping("")
    public String admin(Model model,HttpServletRequest request){
        request.removeAttribute("newsList");
        List<NewsItem> newsList = newsItemRepository.findAll();
        model.addAttribute("newsList",newsList);
        request.getSession().setAttribute("newsList",newsList);
        return "news_admin";
    }
    /*
        修改文章
     */
    @RequestMapping("/updateNews")
    public String updateNews(@RequestParam("id") Long id,Model model){
        NewsItem news = newsItemRepository.findOne(id);
            model.addAttribute("news",news);
            model.addAttribute("content",news.getNewsDetail().getContent());
            return "add_news";
    }


    /**
     * API:获取全部新闻
     * @return
     */
    @RequestMapping(value = "/api/newsList")
    @ResponseBody
    public List<NewsItem> showNewsList(){
        List<NewsItem> newsList = newsItemRepository.findAll();
        if(newsList!=null){
            return newsList;
        }
        return null;
     }

    /**
     * API:获取单页新闻
     * @param pageable
     * @return
     */
    @RequestMapping(value = "/api/news")
    @ResponseBody
    public List<NewsItem> showNewsPage(Pageable pageable){
        return newsItemRepository.findAll(pageable).getContent();
    }

    /**
     * API：获取新闻详情
     */
    @RequestMapping(value = "/api/{id}")
    @ResponseBody
    public NewsDetail showNewsDetail(@PathVariable("id")Long id){
        return newsDetailRepository.findOne(id);
    }



}

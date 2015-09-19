package song.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import song.model.NewsItem;
import song.repository.NewsItemRepository;

import java.util.List;


/**
 * Created by Song on 2015/6/12.
 */
@Controller
@SessionAttributes("newsList")
public class IndexController {
    @Autowired
    private NewsItemRepository newsItemRepository;

    /*
        跳转到主页
     */
    @RequestMapping("/")
    public String home(Model model,Pageable pageable){
        List<NewsItem> newsItemList= newsItemRepository.findAllByOrderByPubTime(pageable);
/*        List<NewsItem> list  = new ArrayList<>(newsItemPage.getContent());
        Collections.sort(list, new Comparator<NewsItem>() {
            @Override
            public int compare(NewsItem o1, NewsItem o2) {
                return o1.getPubTime().compareTo(o2.getPubTime());
            }
        });*/
        model.addAttribute("newsList",newsItemList);
        model.addAttribute("pagenum", pageable.next().getPageNumber());
//        model.addAttribute("pagenum",newsItemPage.hasNext()?newsItemPage.nextPageable().getPageNumber():0);
        return "home";
    }




    /*
        跳转到后台主页
     */
    @RequestMapping("/admin")
    public String index(){
        return  "index";
    }

}

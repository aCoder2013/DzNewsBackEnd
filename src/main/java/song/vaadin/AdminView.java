package song.vaadin;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import org.slf4j.Logger;
import song.event.AddArticleEvent;
import song.model.NewsItem;
import song.repository.NewsDetailRepository;
import song.repository.NewsItemRepository;
import song.utils.EventBusHelper;

import java.util.List;

/**
 * Created by Song on 2015/9/29.
 */
public class AdminView extends CustomComponent implements View {

    public static final String NAME = "adminui";
    private Logger logger = org.slf4j.LoggerFactory.getLogger(AdminView.class);


    private Table table ;

    private EditArticleForm articleForm;
    private NewsItemRepository repository;
    private NewsDetailRepository detailRepository;



    public AdminView(NewsItemRepository repository, NewsDetailRepository detailRepository) {
        this.repository = repository;
        this.detailRepository  = detailRepository;
        articleForm = new EditArticleForm(repository,detailRepository);
    }

    public Table getTable() {
        return table;
    }




    private  void setUp() {
        setSizeFull();
        final List<NewsItem> newsItems = repository.findAll();
        table = new Table();
        table.addContainerProperty("Id",Long.class,null);
        table.addContainerProperty("Title",String.class,null);
        table.addContainerProperty("操作",Button.class,null);
        table.addContainerProperty("删除",Button.class,null);
        setUpTable(newsItems);
        VerticalLayout left = new VerticalLayout(new Label("文章列表"),table);
        left.setSizeFull();
        table.setSizeFull();
        left.setExpandRatio(table, 1);
        HorizontalLayout mainLayout = new HorizontalLayout(new DashboardMenu(),left, articleForm);
        articleForm.setSizeFull();
        mainLayout.setSizeFull();
        mainLayout.setExpandRatio(left, 1);
        mainLayout.setExpandRatio(articleForm,1);
        setCompositionRoot(mainLayout);
    }


    public void refereshTable(){
        table.removeAllItems();
        setUpTable(repository.findAll());
    }


    private void setUpTable(List<NewsItem> newsItems){
        for(NewsItem item:newsItems){
            Long id = item.getId();
            String title = item.getTitle();
            Button change = new Button("修改",FontAwesome.EDIT);
            change.setData(id);
            change.setStyleName("friendly");
            change.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    Long id = (Long) event.getButton().getData();
//                    getCurrent().getPage().setLocation("/news/updateNews?id="+id);
                    articleForm.edit(id);
                }
            });
            Button delete  = new Button("删除",FontAwesome.TRASH_O);
            delete.setStyleName("danger");
            delete.setData(id);
            delete.addClickListener(new Button.ClickListener() {
                @Override
                public void buttonClick(Button.ClickEvent event) {
                    Long id = (Long) event.getButton().getData();
                    repository.delete(id);
                    table.removeItem(id);
                    Notification.show("Id:"+id+"，已经被删除了！");
                }
            });
            table.addItem(new Object[]{id,title,change,delete},id);
        }
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        Notification.show("登陆成功！");
        setUp();
        EventBusHelper.getEventBus().register(this);
    }


    @Subscribe
    public void addArticle(AddArticleEvent event){
        articleForm.add();
        logger.info("New Article!");
    }



}

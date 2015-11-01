package song.core.vaadin;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import song.core.model.NewsDetail;
import song.core.model.NewsItem;
import song.core.service.NewsDetailService;
import song.core.service.NewsItemService;
import song.core.utils.NewsItemBuilder;


/**
 * Created by Song on 2015/9/29.
 */
public class EditArticleForm extends FormLayout implements Property.ValueChangeListener,View {
    public static final String NAME="editarticleform";

    private Button save = new Button("保存");
    private Button cancel = new Button("取消");
    private TextField title = new TextField("标题");
    private TextArea description = new TextArea("简述");
    private TextField auth = new TextField("作者");
    private RichTextArea content = new RichTextArea("内容");



    private boolean changed =false;//标志位,默认内容不发生改变
    NewsItem item;
    BeanFieldGroup<NewsItem> formFieldBing;
    private NewsItemService itemService;
    private NewsDetailService detailService;



    public EditArticleForm(NewsItemService itemService,NewsDetailService detailService) {
        this.itemService = itemService;
        this.detailService = detailService;
        configureComponents();
        setUpValueChangeListener();
        buildLayout();
    }

    private void setUpValueChangeListener() {
        title.addValueChangeListener(this);
        description.addValueChangeListener(this);
        auth.addValueChangeListener(this);
        content.addValueChangeListener(this);
    }

    private void buildLayout() {
        setSizeUndefined();
        setMargin(true);
        HorizontalLayout actions = new HorizontalLayout(save, cancel);
        actions.setSpacing(true);
        addComponents(actions,title,auth,description,content);
    }

    private void configureComponents() {
        save.setStyleName(ValoTheme.BUTTON_PRIMARY);
        save.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        save.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                if(changed){
                    NewsDetail detail = item.getNewsDetail();
                    detail.setContent(content.getValue());
                    detailService.save(detail);
                    item.setAuth(auth.getValue());
                    item.setTitle(title.getValue());
                    item.setDescription(description.getValue());
                    itemService.save(item);
                }
//                getUI().refereshTable();//刷新模型
                Notification.show("save", Notification.Type.TRAY_NOTIFICATION);
            }
        });
        cancel.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Notification.show("cancle", Notification.Type.TRAY_NOTIFICATION);
//                getUI().getTable().select(null);
                setVisible(false);
            }
        });
        title.setImmediate(true);
        auth.setImmediate(true);
        description.setWidth("100%");
        title.setImmediate(true);
        content.setImmediate(true);
        content.setWidth("100%");
        content.setHeight("600px");
        setVisible(false);
    }


    public void add(){
            item = new NewsItemBuilder().setTitle("").setAuth("").setDescription("").newsInstance();
            item.setNewsDetail(new NewsDetail("","",0));
            formFieldBing  = BeanFieldGroup.bindFieldsBuffered(item, this);
            content.setValue("");
            title.focus();
            setVisible(true);
    }

    void edit(Long id){
        this.item = itemService.get(id);
        if(item!=null){
            formFieldBing  = BeanFieldGroup.bindFieldsBuffered(item, this);
            content.setValue(item.getNewsDetail().getContent());
            title.focus();
        }
        setVisible(item!=null);
    }



    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        changed = true;
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        title.focus();
    }
}

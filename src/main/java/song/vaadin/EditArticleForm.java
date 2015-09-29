package song.vaadin;

import com.vaadin.data.Property;
import com.vaadin.data.fieldgroup.BeanFieldGroup;
import com.vaadin.event.ShortcutAction;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import song.model.NewsDetail;
import song.model.NewsItem;
import song.repository.NewsDetailRepository;
import song.repository.NewsItemRepository;



/**
 * Created by Song on 2015/9/29.
 */
public class EditArticleForm extends FormLayout implements Property.ValueChangeListener {

    private Button save = new Button("保存");
    private Button cancel = new Button("取消");
    private TextField title = new TextField("标题");
    private TextArea description = new TextArea("简述");
    private TextField auth = new TextField("作者");
    private RichTextArea content = new RichTextArea("内容");



    private boolean changed =false;//标志位,默认内容不发生改变
    NewsItem item;
    BeanFieldGroup<NewsItem> formFieldBing;
    private NewsItemRepository itemRepository;
    private NewsDetailRepository detailRepository;

    public EditArticleForm() {
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
                    detailRepository.save(detail );
                    item.setAuth(auth.getValue());
                    item.setTitle(title.getValue());
                    item.setDescription(description.getValue());
                    itemRepository.save(item);
                }
                getUI().refereshTable();//刷新模型
                Notification.show("save", Notification.Type.TRAY_NOTIFICATION);
            }
        });
        cancel.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                Notification.show("cancle", Notification.Type.TRAY_NOTIFICATION);
                getUI().getTable().select(null);
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


    void edit(Long id ,NewsItemRepository itemRepository,NewsDetailRepository detailRepository){
        this.itemRepository = itemRepository;
        this.detailRepository = detailRepository;
        this.item = itemRepository.findOne(id);
        if(item!=null){
            formFieldBing  = BeanFieldGroup.bindFieldsBuffered(item, this);
            content.setValue(item.getNewsDetail().getContent());
            title.focus();
        }
        setVisible(item!=null);
    }

    @Override
    public AdminUI getUI() {
        return (AdminUI) super.getUI();
    }

    @Override
    public void valueChange(Property.ValueChangeEvent event) {
        changed = true;
    }
}

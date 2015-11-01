package song.core.vaadin;

import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.event.ShortcutAction;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.Responsive;
import com.vaadin.server.FontAwesome;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import song.core.model.Admin;
import song.core.repository.AdminRepository;
/**
 * Created by Song on 2015/10/8.
 */
public class SimpleLoginView extends VerticalLayout implements View{

    public static final String NAME = "";





    private AdminRepository adminRepository;

    public SimpleLoginView(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
        setSizeFull();
        Component loginForm = buildLoginForm();
        addComponent(loginForm);
        setComponentAlignment(loginForm, Alignment.MIDDLE_CENTER);

    }

    private Component buildLoginForm() {
        final VerticalLayout loginPanel = new VerticalLayout();
        loginPanel.setSizeUndefined();
        loginPanel.setSpacing(true);
        Responsive.makeResponsive(loginPanel);
        loginPanel.addStyleName("login-panel");

        loginPanel.addComponent(buildLabels());
        loginPanel.addComponent(buildFields());
        loginPanel.addComponent(new CheckBox("Remember me", true));
        return loginPanel;
    }

    private Component buildFields() {
        HorizontalLayout fields = new HorizontalLayout();
        fields.setSpacing(true);
        fields.addStyleName("fields");
        final TextField user = new TextField("用户名:");
        user.setIcon(FontAwesome.USER);
        user.setRequired(true);
        user.setInputPrompt("请输入用户名(邮箱地址)");
        user.addValidator(new EmailValidator(
                "Invalid Email Input"));
        user.setInvalidAllowed(false);

        // Create the password input field
        final PasswordField password = new PasswordField("密码:");
        password.setIcon(FontAwesome.LOCK);
        password.addValidator(new PasswordValidator());
        password.setRequired(true);
        password.setValue("请输入密码");
        password.setNullRepresentation("");

        // Create login button
        final Button loginButton = new Button("Login", new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                //
                // Validate the fields using the navigator. By using validors for the
                // fields we reduce the amount of queries we have to use to the database
                // for wrongly entered passwords
                //
                if (!user.isValid() || !password.isValid()) {
                    return;
                }

                String usernameValue = user.getValue();
                String passwordValue = password.getValue();

                //
                // Validate username and password with database here.
                //
                Admin admin =adminRepository.findByEmailAndPassword(usernameValue, passwordValue);
                if (admin!=null) {
                    // Store the current user in the service session
//                    getSession().setAttribute(Admin.class,admin);
                    getSession().setAttribute("admin", admin);
                    // Navigate to main view
                    getUI().getNavigator().navigateTo(AdminView.NAME);

                } else {
                    // Wrong password clear the password field and refocuses it
                    password.setValue(null);
                    password.focus();

                }
            }
        });
        loginButton.setClickShortcut(ShortcutAction.KeyCode.ENTER);
        fields.addComponents(user, password, loginButton);
        fields.setComponentAlignment(loginButton, Alignment.BOTTOM_LEFT);
        return fields;
    }

    private Component buildLabels() {
        CssLayout labels = new CssLayout();
        labels.addStyleName("labels");

        Label welcome = new Label("欢迎登陆DzNews后台管理系统");
        welcome.setSizeUndefined();
        welcome.addStyleName(ValoTheme.LABEL_H2);
        welcome.addStyleName(ValoTheme.LABEL_COLORED);
        labels.addComponent(welcome);
        return labels;
    }


    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // focus the username field when user arrives to the login view
    }

    // Validator for validating the passwords
    private static final class PasswordValidator extends
            AbstractValidator<String> {

        public PasswordValidator() {
            super("The password provided is not valid");
        }

        @Override
        protected boolean isValidValue(String value) {
            //
            // Password must be at least 8 characters long and contain at least
            // one number
            //
            if (value == null) {
                return false;
            }
            return true;
        }

        @Override
        public Class<String> getType() {
            return String.class;
        }
    }


}

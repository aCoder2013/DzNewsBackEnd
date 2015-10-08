package song.vaadin;

import com.vaadin.data.validator.AbstractValidator;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.spring.annotation.SpringView;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.Reindeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import song.model.Admin;
import song.repository.AdminRepository;
/**
 * Created by Song on 2015/10/8.
 */
public class SimpleLoginView extends CustomComponent implements View,
        Button.ClickListener {

    public static final String NAME = "";

    private final TextField user;

    private final PasswordField password;

    private final Button loginButton;

    private AdminRepository adminRepository;

    public SimpleLoginView(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
        setSizeFull();
        // Create the user input field
        user = new TextField("用户名:");
        user.setWidth("300px");
        user.setRequired(true);
        user.setInputPrompt("请输入用户名(邮箱地址)");
        user.addValidator(new EmailValidator(
                "不合法的邮箱地址！"));
        user.setInvalidAllowed(false);

        // Create the password input field
        password = new PasswordField("密码:");
        password.setWidth("300px");
        password.addValidator(new PasswordValidator());
        password.setRequired(true);
        password.setValue("");
        password.setNullRepresentation("");

        // Create login button
        loginButton = new Button("Login", this);

        // Add both to a panel
        VerticalLayout fields = new VerticalLayout(new Label("登陆后台系统"),user, password, loginButton);
        fields.setSpacing(true);
        fields.setMargin(new MarginInfo(true, true, true, false));
        fields.setSizeUndefined();

        // The view root layout
        VerticalLayout viewLayout = new VerticalLayout(fields);
        viewLayout.setSizeFull();
        viewLayout.setComponentAlignment(fields, Alignment.MIDDLE_CENTER);
        viewLayout.setStyleName(Reindeer.LAYOUT_BLUE);
        setCompositionRoot(viewLayout);
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        // focus the username field when user arrives to the login view
        user.focus();
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

        String username = user.getValue();
        String password = this.password.getValue();

        //
        // Validate username and password with database here.
        //
        Admin admin =adminRepository.findByEmailAndPassword(username, password);
        if (admin!=null) {
            // Store the current user in the service session
            getSession().setAttribute("admin", admin);
            // Navigate to main view
            getUI().getNavigator().navigateTo(AdminUI.NAME);

        } else {
            // Wrong password clear the password field and refocuses it
            this.password.setValue(null);
            this.password.focus();

        }
    }
}
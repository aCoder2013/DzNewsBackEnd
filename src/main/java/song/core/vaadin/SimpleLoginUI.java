package song.core.vaadin;

import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.navigator.Navigator;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.UI;
import org.springframework.beans.factory.annotation.Autowired;
import song.core.repository.AdminRepository;
import song.core.service.NewsDetailService;
import song.core.service.NewsItemService;

/**
 * Created by Song on 2015/10/8.
 */
@Title("登陆后台系统")
@Theme("valo")
@SpringUI(path = "/admin")
public class SimpleLoginUI extends UI {

    @Autowired
    private  AdminRepository adminRepository ;

    @Autowired
    private NewsItemService itemService;

    @Autowired
    private NewsDetailService detailService;

    @Override
    protected void init(VaadinRequest request) {

        //
        // Create a new instance of the navigator. The navigator will attach
        // itself automatically to this view.
        //
        new Navigator(this, this);

        //
        // The initial log view where the user can login to the application
        //
        getNavigator().addView(SimpleLoginView.NAME, new SimpleLoginView(adminRepository));//

        //
        // Add the main view of the application
        //
        getNavigator().addView(AdminView.NAME,new AdminView(itemService,detailService));

        //
        // We use a view change handler to ensure the user is always redirected
        // to the login view if the user is not logged in.
        //
        getNavigator().addViewChangeListener(new ViewChangeListener() {

            @Override
            public boolean beforeViewChange(ViewChangeEvent event) {

                // Check if a user has logged in
                boolean isLoggedIn = getSession().getAttribute("admin") != null;
                boolean isLoginView = event.getNewView() instanceof SimpleLoginView;
                boolean isAdminView = event.getNewView() instanceof AdminView;

                if (!isLoggedIn && !isLoginView) {
                    // Redirect to login view always if a user has not yet
                    // logged in
                    getNavigator().navigateTo(SimpleLoginView.NAME);
                    return false;

                } else if (isLoggedIn && isLoginView) {
                    // If someone tries to access to login view while logged in,
                    // then cancel
                    getNavigator().navigateTo(AdminView.NAME);
                    return false;
                }
                return true;
            }

            @Override
            public void afterViewChange(ViewChangeEvent event) {

            }
        });
    }
}
package application.bookstore;
import static org.awaitility.Awaitility.await;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.util.NodeQueryUtils.hasText;
import static org.testfx.util.NodeQueryUtils.isVisible;

import application.bookstore.controllers.LoginController;
import application.bookstore.models.Role;
import application.bookstore.models.User;
import application.bookstore.views.LoginView;
import application.bookstore.views.MainView;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

class MainIntegrationTest extends ApplicationTest {
    private static void seedData() {
        User admin = new User("admin", "Test2022", Role.ADMIN);
        User manager = new User("manager", "Test2022", Role.MANAGER);
        User librarian = new User("librarian", "Test2022", Role.LIBRARIAN);
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(User.FILE_PATH));
            outputStream.writeObject(admin);
            outputStream.writeObject(manager);
            outputStream.writeObject(librarian);
            System.out.println("Wrote users to the file users.ser successfully");
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(Author.FILE_PATH))) {
//            outputStream.writeObject(new Author("Test1", "Test1"));
//            outputStream.writeObject(new Author("Test2", "Test2"));
//            outputStream.writeObject(new Author("Test3", "Test3"));
//            System.out.println("Wrote authors to the file authors.dat successfully");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
    @Override
    public void start(Stage stage) {
        LoginView loginView = new LoginView();
        LoginController controller = new LoginController(loginView, new MainView(), stage);
        Scene scene = new Scene(loginView.getView(), 320, 240);
        stage.setTitle("Bookstore");
        stage.setScene(scene);
        stage.show();
    }
    @Test
    public void testAdminLogIn () throws InterruptedException {

        await().until(() -> lookup("#usernameLabel").query() != null);
        clickOn("#usernameLabel");
        write("admin");
        await().until(() -> lookup("#passwordLabel").query() != null);
        clickOn("#passwordLabel");
        write("Test2022");
        clickOn("#loginBtn");
        verifyThat("#authorTab", isVisible());
        clickOn("#firstNameLabel");
        write("testFX");
        clickOn("#lastNameLabel");
        write("testFX");
        clickOn("#saveBtn");
        verifyThat("#resultLabel", hasText("Author created successfully!"));

    }
}

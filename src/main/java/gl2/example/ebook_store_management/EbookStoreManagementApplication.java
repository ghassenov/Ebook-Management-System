package gl2.example.ebook_store_management;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EbookStoreManagementApplication {
    static {
        Dotenv.configure().load().entries()
                .forEach(e -> System.setProperty(e.getKey(), e.getValue()));
    }

    public static void main(String[] args) {
        SpringApplication.run(EbookStoreManagementApplication.class, args);
    }
}

package springframework.spring6webapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import springframework.spring6webapp.domain.Author;
import springframework.spring6webapp.domain.Book;
import springframework.spring6webapp.repositories.AuthorRepository;
import springframework.spring6webapp.repositories.BookRepository;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author quanvan = new Author();
        quanvan.setFirstName("Quan");
        quanvan.setLastName("Van");

        Book blackOcean = new Book();
        blackOcean.setTitle("Black Ocean");
        blackOcean.setIsbn("1203999211");

        Author quanvanSaved = authorRepository.save(quanvan);
        Book blackOceanSaved = bookRepository.save(blackOcean);

        Author quanvan2 = new Author();
        quanvan.setFirstName("Quan 2");
        quanvan.setLastName("Van");

        Book blackOcean2 = new Book();
        blackOcean2.setTitle("Black Ocean v2");
        blackOcean2.setIsbn("1204999299");

        Author quanvanSaved2 = authorRepository.save(quanvan2);
        Book blackOcean2Saved = bookRepository.save(blackOcean2);

        quanvan.getBooks().add(blackOcean);
        quanvan2.getBooks().add(blackOcean2Saved);

        authorRepository.save(quanvanSaved);
        authorRepository.save(quanvanSaved2);

        System.out.println("In Bootstrap");
        System.out.println("Author count: " + authorRepository.count());
        System.out.println("Book count: " + bookRepository.count());
    }
}

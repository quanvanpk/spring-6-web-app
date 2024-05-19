package springframework.spring6webapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import springframework.spring6webapp.domain.Author;
import springframework.spring6webapp.domain.Book;
import springframework.spring6webapp.domain.Publisher;
import springframework.spring6webapp.repositories.AuthorRepository;
import springframework.spring6webapp.repositories.BookRepository;
import springframework.spring6webapp.repositories.PublisherRepository;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author author1 = new Author();
        author1.setFirstName("Quan");
        author1.setLastName("Van");

        Book blackOcean = new Book();
        blackOcean.setTitle("Black Ocean");
        blackOcean.setIsbn("1203999211");

        Author author1Saved = authorRepository.save(author1);
        Book blackOceanSaved = bookRepository.save(blackOcean);

        Author author2 = new Author();
        author2.setFirstName("Quan 2");
        author2.setLastName("Van");

        Book blackOcean2 = new Book();
        blackOcean2.setTitle("Black Ocean v2");
        blackOcean2.setIsbn("1204999299");

        Author author2Saved = authorRepository.save(author2);
        Book blackOcean2Saved = bookRepository.save(blackOcean2);

        author1Saved.getBooks().add(blackOceanSaved);
        author2Saved.getBooks().add(blackOcean2Saved);
        blackOceanSaved.getAuthors().add(author1Saved);
        blackOcean2Saved.getAuthors().add(author2Saved);

        Publisher publisher = new Publisher();
        publisher.setPublisherName("Nha Nam");
        publisher.setAddress("HCM, VietNam");
        publisher.setAddress("District 1");
        publisher.setCity("Ho Chi Minh");
        Publisher publisherSaved = publisherRepository.save(publisher);

        blackOceanSaved.setPublisher(publisherSaved);
        blackOcean2Saved.setPublisher(publisherSaved);
        
        authorRepository.save(author1Saved);
        authorRepository.save(author2Saved);
        bookRepository.save(blackOceanSaved);
        bookRepository.save(blackOcean2Saved);

        System.out.println("In Bootstrap");
        System.out.println("Author count: " + authorRepository.count());
        System.out.println("Book count: " + bookRepository.count());
        System.out.println("Publisher count: " + publisherRepository.count());
    }
}

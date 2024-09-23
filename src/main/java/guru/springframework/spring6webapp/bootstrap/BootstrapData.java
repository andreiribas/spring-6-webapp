package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
        System.out.println("Hello from Bootstrap");

        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Evans");

        Book ddd = new Book();
        ddd.setTitle("Domain Driven Design");
        ddd.setIsbn("1234");

        var addisonWesley = new Publisher();
        addisonWesley.setPublisherName("Addison Wesley");
        addisonWesley.setAddress("Jacob Way");
        addisonWesley.setCity("Reading");
        addisonWesley.setState("MA");
        addisonWesley.setZip("01867");

        var ericSaved = authorRepository.save(eric);
        var dddSaved = bookRepository.save(ddd);
        var addisonWesleySaved = publisherRepository.save(addisonWesley);

        Author rod = new Author();
        rod.setFirstName("Rod");
        rod.setLastName("Johnson");

        Book noEJB = new Book();
        noEJB.setTitle("J2EE Development without EJB");
        noEJB.setIsbn("568752");

        var wroxPress = new Publisher();
        wroxPress.setPublisherName("Wrox Press");
        wroxPress.setAddress("111 River Street");
        wroxPress.setCity("Hoboken");
        wroxPress.setState("NJ");
        wroxPress.setZip("070030");

        var rodSaved = authorRepository.save(rod);
        var noEJBSaved = bookRepository.save(noEJB);
        var wroxPressSaved = publisherRepository.save(wroxPress);

        dddSaved.setPublisher(addisonWesleySaved);
        dddSaved.getAuthors().add(ericSaved);
        dddSaved = bookRepository.save(dddSaved);
        ericSaved.getBooks().add(dddSaved);
        ericSaved = authorRepository.save(ericSaved);

        noEJBSaved.setPublisher(wroxPressSaved);
        noEJBSaved.getAuthors().add(rodSaved);
        noEJBSaved = bookRepository.save(noEJBSaved);
        rodSaved.getBooks().add(noEJBSaved);
        rodSaved = authorRepository.save(rodSaved);

        System.out.println("Author Count: " + authorRepository.count());
        System.out.println("Book Count: " + bookRepository.count());
        System.out.println("Publisher Count: " + publisherRepository.count());

    }
}

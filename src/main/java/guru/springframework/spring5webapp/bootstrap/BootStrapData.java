package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.model.Author;
import guru.springframework.spring5webapp.model.Book;
import guru.springframework.spring5webapp.model.Publisher;
import guru.springframework.spring5webapp.repository.AuthorRepository;
import guru.springframework.spring5webapp.repository.BookRepository;
import guru.springframework.spring5webapp.repository.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner
{
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository)
    {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception
    {
        Publisher oReilly = new Publisher("OReilly", "Address Line 1", "Chicago", "Illinois", "ZIP Code");

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");
        eric.getBooks().add(ddd);
        ddd.setPublisher(oReilly);
        ddd.getAuthors().add(eric);
        oReilly.getBooks().add(ddd);

        authorRepository.save(eric);
        // Save the publisher and the book is also saved due to the cascade type
        publisherRepository.save(oReilly);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "3939459459");
        rod.getBooks().add(noEJB);
        noEJB.setPublisher(oReilly);
        noEJB.getAuthors().add(rod);
        oReilly.getBooks().add(noEJB);

        authorRepository.save(rod);
        // Save the publisher and the book is also saved due to the cascade type
        publisherRepository.save(oReilly);

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Books: " + bookRepository.count());
        System.out.println("Number of Publishers: " + publisherRepository.count());
        System.out.println("OReilly No. of Books: " + oReilly.getBooks().size());
    }
}

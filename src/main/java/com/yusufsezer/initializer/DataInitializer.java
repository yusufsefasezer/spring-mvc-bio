package com.yusufsezer.initializer;

import com.github.javafaker.Faker;
import com.github.javafaker.Internet;
import com.github.javafaker.Name;
import com.yusufsezer.entity.Author;
import com.yusufsezer.entity.Comment;
import com.yusufsezer.entity.Page;
import com.yusufsezer.entity.Person;
import com.yusufsezer.entity.Setting;
import com.yusufsezer.enumeration.Role;
import com.yusufsezer.enumeration.Status;
import com.yusufsezer.repository.AuthorRepository;
import com.yusufsezer.repository.CommentRepository;
import com.yusufsezer.repository.PageRepository;
import com.yusufsezer.repository.PersonRepository;
import com.yusufsezer.repository.SettingRepository;
import com.yusufsezer.util.EnumUtil;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private final Faker faker;

    private final PasswordEncoder passwordEncoder;

    private final AuthorRepository authorRepository;

    private final CommentRepository commentRepository;

    private final PageRepository pageRepository;

    private final PersonRepository personRepository;

    private final SettingRepository settingRepository;

    public DataInitializer(
            Faker faker,
            PasswordEncoder passwordEncoder,
            AuthorRepository authorRepository,
            CommentRepository commentRepository,
            PageRepository pageRepository,
            PersonRepository personRepository,
            SettingRepository settingRepository) {
        this.faker = faker;
        this.passwordEncoder = passwordEncoder;
        this.authorRepository = authorRepository;
        this.commentRepository = commentRepository;
        this.pageRepository = pageRepository;
        this.personRepository = personRepository;
        this.settingRepository = settingRepository;
    }

    @PostConstruct
    public void init() {
        initAdmin();
        initAuthor(5);
        initPerson(200);
        initComment(10);
        initPage(5);
        initSetting();
    }

    private void initAdmin() {
        Author author = new Author();
        author.setFirstName("Yusuf");
        author.setLastName("Sezer");
        author.setDescription("yusufsezer.com");
        author.setEmail("yusufsezer@mail.com");

        String password = passwordEncoder.encode("123456");
        author.setPassword(password);

        author.setBirthDate(LocalDate.of(1970, Month.JANUARY, 1));
        author.setRole(Role.ADMINISTRATOR);
        author.setActive(true);
        authorRepository.save(author);
    }

    public void initAuthor(int size) {
        List<Author> authors = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            Author newAuthor = new Author();
            Name name = faker.name();

            String firstName = name.firstName();
            newAuthor.setFirstName(firstName);

            String lastName = name.lastName();
            newAuthor.setLastName(lastName);

            String description = faker.lorem().paragraph(3);
            //String description = faker.lorem().characters(250);
            newAuthor.setDescription(description);

            Internet internet = faker.internet();
            String email = internet.emailAddress();
            newAuthor.setEmail(email);

            String fakePassword = internet.password();
            String encodedPassword = passwordEncoder.encode(fakePassword);
            newAuthor.setPassword(encodedPassword);

            LocalDate birthDate = faker.date().birthday()
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            newAuthor.setBirthDate(birthDate);

            Role role = EnumUtil.getRandom(Role.class);
            newAuthor.setRole(role);

            boolean active = ThreadLocalRandom.current().nextBoolean();
            newAuthor.setActive(active);

            authors.add(newAuthor);
        }

        authorRepository.saveAll(authors);
    }

    public void initComment(int size) {
        List<Comment> comments = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            Comment newComment = new Comment();

            String content = faker.lorem().paragraph(2);
            //String content = faker.lorem().characters(250);
            newComment.setContent(content);

            boolean active = ThreadLocalRandom.current().nextBoolean();
            newComment.setActive(active);

            Author author = null;
            long countOfAuthor = authorRepository.count();
            int rndAuthorNumber = (int) (Math.random() * countOfAuthor);
            PageRequest authorPageRequest = PageRequest.of(rndAuthorNumber, 1);

            org.springframework.data.domain.Page<Author> foundAuthor
                    = authorRepository.findAll(authorPageRequest);
            if (foundAuthor.hasContent()) {
                author = foundAuthor.getContent().get(0);
            }
            newComment.setAuthor(author);

            Person person = null;
            long countOfPerson = personRepository.count();
            int rndPersonNumber = (int) (Math.random() * countOfPerson);
            PageRequest personPageRequest = PageRequest.of(rndPersonNumber, 1);

            org.springframework.data.domain.Page<Person> foundPerson
                    = personRepository.findAll(personPageRequest);
            if (foundPerson.hasContent()) {
                person = foundPerson.getContent().get(0);
            }
            newComment.setPerson(person);

            comments.add(newComment);
        }

        commentRepository.saveAll(comments);
    }

    public void initPerson(int size) {
        List<Person> persons = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            Person newPerson = new Person();
            Name name = faker.name();

            String firstName = name.firstName();
            newPerson.setFirstName(firstName);

            String lastName = name.lastName();
            newPerson.setLastName(lastName);

            String description = faker.lorem().paragraph(3);
            //String description = faker.lorem().characters(250);
            newPerson.setDescription(description);

            //String photo = faker.avatar().image();
            Integer id = faker.random().nextInt(0, 70);
            String photo = String.format("https://i.pravatar.cc/300?img=%s", id);
            newPerson.setPhoto(photo);

            String profession = name.title();
            newPerson.setProfession(profession);

            Status status = EnumUtil.getRandom(Status.class);
            newPerson.setStatus(status);

            Map<String, String> features = new HashMap<>();
            features.put("Blood Group", faker.name().bloodGroup());
            features.put("Middle Name", faker.name().nameWithMiddle());
            features.put("Prefix", faker.name().prefix());
            features.put("Suffix", faker.name().suffix());
            newPerson.setFeatures(features);

            persons.add(newPerson);
        }

        personRepository.saveAll(persons);
    }

    public void initPage(int size) {
        List<Page> pages = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            Page newPage = new Page();

            String title = faker.book().title();
            newPage.setTitle(title);

            String excerpt = faker.lorem().paragraph();
            newPage.setExcerpt(excerpt);

            String description = faker.lorem().paragraph(3);
            //String description = faker.lorem().characters(250);
            newPage.setDescription(description);

            Status status = EnumUtil.getRandom(Status.class);
            newPage.setStatus(status);

            pages.add(newPage);
        }

        pageRepository.saveAll(pages);
    }

    public void initSetting() {
        Setting title = new Setting();
        title.setSKey("title");
        title.setSValue("Spring Biography");

        Setting description = new Setting();
        description.setSKey("description");
        description.setSValue("Lorem Ipsum is simply dummy text of the printing and typesetting industry");

        settingRepository.saveAll(Arrays.asList(title, description));
    }

    @PreDestroy
    public void destroy() {
//        authorRepository.deleteAll();
//        commentRepository.deleteAll();
//        pageRepository.deleteAll();
//        personRepository.deleteAll();
//        settingRepository.deleteAll();
    }

}

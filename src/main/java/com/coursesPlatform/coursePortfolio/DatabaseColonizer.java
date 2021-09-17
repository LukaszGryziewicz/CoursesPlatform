package com.coursesPlatform.coursePortfolio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import static java.math.BigDecimal.*;

@Component
@Profile("prod")
public class DatabaseColonizer implements CommandLineRunner {
    private final CategoryService categoryService;
    private final CourseService courseService;
    private final LectureService lectureService;
    private final CustomerService customerService;

    public DatabaseColonizer(CategoryService categoryService, CourseService courseService, LectureService lectureService, CustomerService customerService) {
        this.categoryService = categoryService;
        this.courseService = courseService;
        this.lectureService = lectureService;
        this.customerService = customerService;
    }

    @Override
    public void run(String... args) {
        CategoryDTO category = new CategoryDTO("IT", "Kursy IT");
        CategoryDTO category2 = new CategoryDTO("Sport", "Kursy Sportowe");
        CategoryDTO category3 = new CategoryDTO("Medica", "Kursy Medica");
        CategoryDTO category4 = new CategoryDTO("IT", "Kursy IT");
        categoryService.add(category);
        categoryService.add(category2);
        categoryService.add(category3);
        categoryService.add(category4);
        CourseDTO course = new CourseDTO("Java", "Technologia Java to podstawa projektowania niemal każdego rodzaju aplikacji sieciowej — stanowi ogólnoświatowy standard wykorzystywany przy tworzeniu aplikacji dla urządzeń mobilnych, aplikacji wbudowywanych, gier, zawartości i treści internetowych oraz oprogramowania dla przedsiębiorstw. Społeczność ponad 9 milionów oddanych programistów przyczynia się do sprawnego tworzenia, implementacji oraz użytkowania aplikacji i usług.");
        CourseDTO course2 = new CourseDTO("C#", "C# jest wysokopoziomowym, obiektowym językiem programowania ogólnego przeznaczenia, który jest odpowiedzią Microsoftu na Jave. C# jest ścisłe zintegrowany z platformą .NET, która jest zarówno frameworkiem jak i środowiskiem uruchomieniowym C# był stworzony i jest najczęściej stosowany do pisania aplikacji na systemy Windows, ale odkąd .NET framework trafił na systemy Linux i Mac możliwe jest tworzenie natywnego oprogramowania w tym języku praktycznie na każdą platformę.Ponadto, C# używany jest do tworzenia aplikacji webowych po stronie serwera za pomocą frameworka ASP.NET.");
        CourseDTO course3 = new CourseDTO("JavaScript", "JavaScript jest to skryptowy język programowania wysokiego poziomu.JavaScript jest językiem wieloparadygmatowym - można w nim programować obiektowo, funkcyjnie i imperatywnie. Posiada API przystosowane do pracy z tekstami, tablicami, datami czy wyrażeniami regularnymi. Razem z HTML i CSS JavaScript stanowi podstawowe narzędzie do tworzenia stron internetowych, jednak posiada również szersze zastosowania - jest używany w aplikacjach desktopowych i dokumentach PDF oraz w aplikacjach webowych po stronie serwera.");
        CourseDTO course4 = new CourseDTO("PHP", "PHP jest skryptowym językiem programowania, który wykorzystuje się głównie do tworzenia stron internetowych i aplikacji. Jest to najpopularniejszy język w świecie aplikacji Internetowych. To przy jego pomocy stworzono aplikacje: Facebook, Wikipedia, WordPress, Flickr czy serwis Tumblr.");
        courseService.add(course, category.getTitle());
        courseService.add(course2, category.getTitle());
        courseService.add(course3, category.getTitle());
        courseService.add(course4, category.getTitle());
        LectureDTO lecture = new LectureDTO("Podstawy programowania obiektowego","Programowanie obiektowe lub inaczej programowanie zorientowane obiektowo (ang. object-oriented programing, OOP) to paradygmat programowania przy pomocy obiektów posiadających swoje właściwości jak pola (dane, informacje o obiekcie) oraz metody (zachowanie, działania jakie wykonuje obiekt). Programowanie obiektowe polega na definiowaniu obiektów oraz wywoływaniu ich metod, tak aby współdziałały wzajemnie ze sobą.",valueOf(3000),150);
        LectureDTO lecture2 = new LectureDTO("Spring Framework","Spring jest platformą złożoną z wielu projektów, która dedykowana jest do tworzenia aplikacji w języku Java. Jego kluczowym elementem jest kontener wstrzykiwania zależności, jednak przez lata Spring zyskał wsparcie dla wielu technologii i stanowi dziś jeden z kluczowych elementów całego ekosystemu Javy.", valueOf(2500),100);
        LectureDTO lecture3 = new LectureDTO("Hibernate","Hibernate framework stanowiący jedną z najpopularniejszych implementacji Java Persistence API (JPA). Umożliwia on dokonania mapowania obiektowo-relacyjnego (ORM) – czyli odwzorowania obiektów klas na bazę danych. Najprościej mówiąc odpowiada za wygodna komunikację aplikacji z bazą danych.", valueOf(1500),50);
        LectureDTO lecture4 = new LectureDTO("REST API w Springu","REST API to jeden z najpopularniejszych stylów architektonicznych, którego znajomość jest niezbędna na rynku pracy backend developerów.",valueOf(250),10);
        lectureService.add(lecture,course.getTitle());
        lectureService.add(lecture2,course.getTitle());
        lectureService.add(lecture3,course.getTitle());
        lectureService.add(lecture4,course.getTitle());
        CustomerDTO customer = new CustomerDTO("Adam Dominik","kurs.inqoo@gmail.com","123456789");
        customerService.add(customer);
    }
}

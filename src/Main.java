import model.Summary;
import repository.SummaryRepository;
import services.Serializer;

import java.io.FileNotFoundException;
import java.time.LocalDate;

/**
 * Created by szkutek on 12.06.17.
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException {
        SummaryRepository repository = new SummaryRepository();
        repository.addItem(LocalDate.now(), new Summary());

        Serializer.serialize(repository, Configuration.SUMMARY_REPOSITORY_FILE);
        SummaryRepository deserialize = (SummaryRepository) Serializer.deserialize(Configuration.SUMMARY_REPOSITORY_FILE);

        System.out.println();
    }
}

package bp.config;

import bp.model.Summary;
import bp.repository.SummaryRepository;
import bp.services.Serializer;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by szkutek on 12.06.17.
 */
public class Main {

    public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException {
        SummaryRepository repository = new SummaryRepository();
        repository.addItem(LocalDate.now(), new Summary(LocalDate.now()));

        Serializer.serialize(repository, Configuration.SUMMARY_REPOSITORY_FILE);
        SummaryRepository deserialize = (SummaryRepository) Serializer.deserialize(Configuration.SUMMARY_REPOSITORY_FILE);

        System.out.println();
    }
}

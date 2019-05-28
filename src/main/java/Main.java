import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {



        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String path = reader.readLine();
        String pathReport = reader.readLine();

        BufferedReader br = new BufferedReader(new FileReader(path));


        Person[] sample = new ObjectMapper().readValue(new File(path), Person[].class);
        Report report = new ObjectMapper().readValue(new File(pathReport), Report.class);

        List<Person> list = new LinkedList<Person>(Arrays.asList(sample));
//        C:\Users\email\Desktop\text.json
//        C:\Users\email\Desktop\report.json
        list.forEach(person -> person.setScore(report));
        List<Person> personList = list.stream().filter(isSalesPeriodOk(report)).filter(isScoreOk(report))
                .collect(Collectors.<Person>toList());

        File file = new File("C:\\Users\\email\\Desktop\\output.txt");
        if ( file.createNewFile()) {
            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file.getPath()), "utf-8"))) {
                StringBuilder output = new StringBuilder();
                output.append(personList.size() > 0 ? "Name  ,Score" : "");

                for (Person person : personList) {

                    output.append(System.lineSeparator());
                    output.append(person.toString());

                }
                writer.write(output.toString());
            }
        }


    }

    private static Predicate<Person> isSalesPeriodOk(Report report) {
        return p -> p.getSalesPeriod() <= report.getPeriodLimit();
    }

    private static Predicate<Person> isScoreOk(Report report) {

        if (report.getUseExprienceMultiplier()) {

            return (person) ->
                    person.getTotalSales() / person.getSalesPeriod() * person.getExperienceMultiplier() <= report.getTopPerformersThreshold();

        } else {
            return person -> person.getTotalSales() / person.getSalesPeriod() <= report.getTopPerformersThreshold();

        }


    }

}



package domain;

import json.*;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class Student extends BasicStudent {
    private ArrayList<Tuple<String, Integer>> exams;

    @SafeVarargs
    public Student(String name, String surname, Integer year, Tuple<String, Integer>... exams) {
        super(name, surname, year);
        this.exams = new ArrayList(Arrays.asList(exams));
    }

    @Override
    public JsonObject toJsonObject() {
        JsonPair name = new JsonPair("name", new JsonString(this.name));
        JsonPair surname = new JsonPair("surname", new JsonString(this.surname));
        JsonPair year = new JsonPair("year", new JsonNumber(this.year));
        JsonObject[] ExamsToInsert = new JsonObject[exams.size()];
        for (int i = 0; i < exams.size(); i++) {
            Tuple<String, Integer> exam = exams.get(i);
            JsonObject CurObj = new JsonObject();
            CurObj.add(new JsonPair("course", new JsonString(exam.key)));
            CurObj.add(new JsonPair("mark", new JsonNumber(exam.value)));
            boolean passed = exam.value > 2;
            CurObj.add(new JsonPair("passed", new JsonBoolean(passed)));
            ExamsToInsert[i] = CurObj;
        }
        return new JsonObject(name, surname, year, new JsonPair("exams", new JsonArray(ExamsToInsert)));
    }
}
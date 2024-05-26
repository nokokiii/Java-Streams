package ex2;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class UserAnalyzer {
    private final List<User> users;

    public UserAnalyzer(List<User> users) {
        this.users = users;
    }

    protected double calculateAverageAge() {
        return users.stream().mapToInt(User::age).average().orElse(0);
    }

    protected List<String> findCountriesWithMostUsers() {
        Map<String, Long> countryAmount = users.stream().collect(groupingBy(User::country, counting()));
        long mostUsers = countryAmount.values().stream().max(Long::compareTo).orElse(0L);

        return countryAmount.entrySet().stream().filter(country -> country.getValue() == mostUsers).map(Map.Entry::getKey).toList();
    }

    private String mostCommon(Map<String, Long> map) {
        return map.entrySet().stream().max(Map.Entry.comparingByValue()).map(Map.Entry::getKey).orElse("");
    }

    protected String findMostCommonFirstName() {
        return mostCommon(users.stream().map(User::firstName).collect(groupingBy(String::toString, counting())));

    }

    protected String findMostCommonLastName() {
        return mostCommon(users.stream().map(User::lastName).collect(groupingBy(String::toString, counting())));
    }

    protected User findOldestUser() {
        return users.stream().max(comparing(User::age)).orElse(null);
    }

    protected User findYoungestUser() {
        return users.stream().min(comparing(User::age)).orElse(null);
    }

    protected List<String> findUniqueCountries() {
        return users.stream().map(User::country).distinct().toList();
    }
}

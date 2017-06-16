package com.udacity.builditbigger.jokeprovider;

import java.util.Random;

/**
 * This class handles the storage of jokes as Strings.
 *
 * @author Robert
 * Created on 6/14/2017
 */
public final class JokeProvider {
    private static String[] jokes = {
            "What is an algorithm? Something that programmers say when they don't want to explain what they did!",
            "What is hardware? The part of a computer that you can kick!",
            "What is a programmer? Someone who fixed a problem you don't know you have, in a way you don't understand!",
            "What is the object-oriented way to becoming wealthy? Inheritance!",
            "What is a programmers favorite place to hang out? Foo bar!",
            "Why did the programmer quit his job? Because he didn't get arrays!",
            "What do computers and AC units have in common? They both become useless when you open Windows!",
            "How can you tell if you're using HTML5? If it doesn't work in Internet Explorer!",
            "A Java developer had a problem. It's now a ProblemFactory!"
    };

    /**
     * This method uses Java's Random class to provide a pseudo-random joke.
     *
     * @return A String representing the selected joke.
     */
    public static String randomJoke() {
        Random random = new Random();
        int index = random.nextInt(jokes.length);

        return jokes[index];
    }
}

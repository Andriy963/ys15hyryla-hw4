package ua.yandex.fj;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import ua.yandex.fj.WordCount.WordCountTask;

/**
 *
 * @author Andrii Hyryla
 */
public class WordCountTest {

    @Test
    public void computeTestWithTenWords() {
        
        String[] s = {"java", "fork", "join", "java", "java", "join", "fork", "join", "java", "fork"};
        Map<String, Integer> expRes = new HashMap<>();
        expRes.put("java", 4);
        expRes.put("fork", 3);
        expRes.put("join", 3);
        
        ForkJoinPool fjPool = ForkJoinPool.commonPool();
        Map<String, Integer> res = fjPool.invoke(new WordCountTask(s, 0, 9));
        
        assertTrue(expRes.equals(res));
    }
    
    @Test
    public void computeTestWithHundredWords() {
        
        String[] s = {"java", "fork", "join", "java", "java", "join", "fork", "join", "java", "fork",
        "java", "fork", "join", "java", "java", "join", "fork", "join", "java", "fork",
        "java", "fork", "join", "java", "java", "join", "fork", "join", "java", "fork",
        "java", "fork", "join", "java", "java", "join", "fork", "join", "java", "fork",
        "java", "fork", "join", "java", "java", "join", "fork", "join", "java", "fork",
        "java", "fork", "join", "java", "java", "join", "fork", "join", "java", "fork",
        "java", "fork", "join", "java", "java", "join", "fork", "join", "java", "fork",
        "java", "fork", "join", "java", "java", "join", "fork", "join", "java", "fork",
        "java", "fork", "join", "java", "java", "join", "fork", "join", "java", "fork",
        "java", "fork", "join", "java", "java", "join", "fork", "join", "java", "fork"};
        Map<String, Integer> expRes = new HashMap<>();
        expRes.put("java", 40);
        expRes.put("fork", 30);
        expRes.put("join", 30);
        
        ForkJoinPool fjPool = ForkJoinPool.commonPool();
        Map<String, Integer> res = fjPool.invoke(new WordCountTask(s, 0, 99));
        
        assertTrue(expRes.equals(res));
    }
    
    @Test
    public void computeTestWithNinetyWords() {
        
        String[] s = {"java", "fork", "join", "java", "java", "join", "fork", "join", "java", "fork",
        "qwerty", "qwerty", "qwerty", "qwerty", "qwerty", "qwerty", "qwerty", "qwerty", "qwerty", "qwerty",
        "j", "f", "j", "j", "j", "j", "f", "j", "j", "f",
        "java", "fork", "join", "java", "java", "join", "fork", "join", "java", "fork",
        "qwerty", "qwerty", "qwerty", "qwerty", "qwerty", "qwerty", "qwerty", "qwerty", "qwerty", "qwerty",
        "j", "f", "j", "j", "j", "j", "f", "j", "j", "f",
        "java", "fork", "join", "java", "java", "join", "fork", "join", "java", "fork",
        "qwerty", "qwerty", "qwerty", "qwerty", "qwerty", "qwerty", "qwerty", "qwerty", "qwerty", "qwerty",
        "j", "f", "j", "j", "j", "j", "f", "j", "j", "f"};
        Map<String, Integer> expRes = new HashMap<>();
        expRes.put("java", 12);
        expRes.put("fork", 9);
        expRes.put("join", 9);
        expRes.put("qwerty", 30);
        expRes.put("j", 21);
        expRes.put("f", 9);
        
        ForkJoinPool fjPool = ForkJoinPool.commonPool();
        Map<String, Integer> res = fjPool.invoke(new WordCountTask(s, 0, 89));
        
        assertTrue(expRes.equals(res));
    }
    
    @Test
    public void computeTestWithAllDifferentWords() {
        
        String[] s = {"java", "fork", "join", "pen", "fox", "hand", "left", "right",
        "joy", "util", "cun", "current", "bla", "andrii", "hyryla"};
        Map<String, Integer> expRes = new HashMap<>();
        expRes.put("java", 1);
        expRes.put("fork", 1);
        expRes.put("join", 1);
        expRes.put("pen", 1);
        expRes.put("fox", 1);
        expRes.put("hand", 1);
        expRes.put("left", 1);
        expRes.put("right", 1);
        expRes.put("joy", 1);
        expRes.put("util", 1);
        expRes.put("cun", 1);
        expRes.put("current", 1);
        expRes.put("bla", 1);
        expRes.put("andrii", 1);
        expRes.put("hyryla", 1);
        
        ForkJoinPool fjPool = ForkJoinPool.commonPool();
        Map<String, Integer> res = fjPool.invoke(new WordCountTask(s, 0, 14));
        
        assertTrue(expRes.equals(res));
    }
}

package ua.yandex.fj;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.RecursiveTask;

/**
 *
 * @author Andrii Hyryla
 */
public class WordCount {

    static class WordCountTask extends RecursiveTask<Map<String, Integer>> {

        private final String[] s;
        private final int left;
        private final int right;
        private Map<String, Integer> result = new HashMap<>();

        public WordCountTask(String[] s, int left, int right) {
            this.s = s;
            this.left = left;
            this.right = right;
        }

        private Map<String, Integer> computeSequential() {
            result.put(s[left], 1);
            return result;
        }

        @Override
        protected Map<String, Integer> compute() {
            if (left == right) {
                return computeSequential();
            }
            WordCountTask leftTask
                    = new WordCountTask(s, left, (left + right) / 2);
            WordCountTask rightTask
                    = new WordCountTask(s, (left + right) / 2 + 1, right);
            rightTask.fork();
            Map<String, Integer> leftMap = leftTask.compute();
            Map<String, Integer> rightMap = rightTask.join();
            return addTwoMaps(leftMap, rightMap);
        }
        
        private Map<String, Integer> addTwoMaps(Map<String, Integer> leftMap, 
                Map<String, Integer> rightMap) {
            Set<String> keySet = leftMap.keySet();
            for (String key : keySet) {
                if (rightMap.containsKey(key)) {
                    leftMap.put(key, leftMap.get(key) + rightMap.get(key));
                    rightMap.remove(key);
                }
            }
            leftMap.putAll(rightMap);
            return leftMap;
        }
    }
}

import java.util.*;

public class Main {

    static boolean person_is_seller(String name) {
        return name == "nguyen";
    }

    static void breath_first_search(Map<String, List<String>> graph) {
        var searchQueue = new PriorityQueue<String>();
        var searched = new HashSet<String>();
        searchQueue.addAll(graph.get("you"));
        while (!searchQueue.isEmpty()) {
            String person = searchQueue.poll();
            if (!searched.contains(person)) {
                if (person_is_seller(person)) {
                    System.out.println(person + " is mango seller!");
                    return;
                } else {
                    searchQueue.addAll(graph.get(person));
                    searched.add(person);
                }
            }
        }
        System.out.println("No mango seller!");
    }

    static boolean isFile(String dir) {
        return dir.contains(".");
    }

    static void browse_file(Map<String, List<String>> directoriesTree, String root) {
        var queue = new PriorityQueue<String>();
        queue.addAll(directoriesTree.get(root));
        while (!queue.isEmpty()) {
            String dir = queue.poll();
            if (isFile(dir)) {
                System.out.println(dir);
            } else {
                queue.addAll(directoriesTree.get(dir));
            }
        }
    }

    static void browse_file_recursive(Map<String, List<String>> directoriesTree, String dir) {
        for (String child:  directoriesTree.get(dir)) {
            if (isFile(child))
                System.out.println(child);
            else
                browse_file(directoriesTree, child);
        }
    }

    public static void main(String[] args) {
        Map<String, List<String>> graph = new Hashtable<>();
        // alice, bob and claire are out-neighbors of you
        graph.put("you", List.of("alice", "bob", "claire"));
        graph.put("bob", List.of("anuj", "peggy"));
        graph.put("alice", List.of("peggy"));
        graph.put("claire", List.of("thom", "jonny"));
        graph.put("anuj", List.of());
        graph.put("peggy", List.of());
        graph.put("thom", List.of());
        graph.put("jonny", List.of());

        breath_first_search(graph);

        Map<String, List<String>> graph2 = new Hashtable<>();
        graph2.put("pics", List.of("2001", "odyssey.png"));
        graph2.put("2001", List.of("a.png", "space.png"));
        System.out.println("===============Breath First Search===============");
        browse_file(graph2, "pics");

        System.out.println("===============Breath First Search (Recursive)===============");
        browse_file_recursive(graph2, "pics");
    }
}
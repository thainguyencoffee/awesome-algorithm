import java.util.*;

//#A Tìm node có chi phí thấp nhất mà bạn chưa xử lý.
//#B Nếu bạn đã xử lý tất cả các nút, vòng lặp while này đã hoàn thành.
//#C Duyệt qua tất cả các hàng xóm của nút này.
//#D Nếu việc đi đến neighbor này thông qua node này là rẻ hơn...
//#E ... cập nhật chi phí cho node này.
//#F Node này trở thành cha mới cho neighbor này.
//#G Đánh dấu nút đã được xử lý.
//#H Tìm nút tiếp theo để xử lý và lặp lại.

public class Main {

    static String lowest_cost_node(Set<String> processed, Map<String, Integer> costs) {
        var lowest_cost = Integer.MAX_VALUE;
        String lowest_node_node = null;

        for (var node : costs.keySet()) {
            var cost = costs.get(node);
            if (cost < lowest_cost && !processed.contains(node)) {
                lowest_cost = cost;
                lowest_node_node = node;
            }
        }
        return lowest_node_node;
    }

    static void dijkstra(Map<String, Integer> costs,
                         Map<String, Map<String, Integer>> graph,
                         Map<String, String> parents) {
        Set<String> processed = new HashSet<>();
        var node = lowest_cost_node(processed, costs);
        while (node != null) {

            Map<String, Integer> neighbors = graph.get(node);
            for (var neighbor: neighbors.keySet()) {
                Integer neighbor_cost = neighbors.get(neighbor);
                Integer cost = costs.get(node);
                Integer newCost = cost + neighbor_cost;
                if (costs.get(neighbor) > newCost) {
                    costs.put(neighbor, newCost);
                    parents.put(neighbor, node);
                }
            }

            processed.add(node);
            node = lowest_cost_node(processed, costs);
        }
    }

    // node 'start' is start node
    public static void main(String[] args) {
        // Cách mô hình 1 đồ thị có hướng
        Map<String, Map<String, Integer>> graph = new Hashtable<>();
        graph.put("A", Map.of(
                "B", 17,
                "C", 18,
                "D", 15)
        );

        graph.put("B", Map.of("G", 27, "E", 18));
        graph.put("C", Map.of("E", 15, "F", 10));
        graph.put("D", Map.of("F", 14, "I", 29));
        graph.put("E", Map.of("G", 6, "H", 12));
        graph.put("F", Map.of("E", 4, "I", 13, "H", 17));
        graph.put("G", Map.of("J", 15));
        graph.put("H", Map.of("J", 11));
        graph.put("I", Map.of("J", 13, "H", 3));
        graph.put("J", Map.of());

        Map<String, Integer> costs = new Hashtable<>();
        costs.put("A", 0);
        costs.put("B", 17);
        costs.put("C", 18);
        costs.put("D", 15);
        costs.put("E", Integer.MAX_VALUE);
        costs.put("F", Integer.MAX_VALUE);
        costs.put("G", Integer.MAX_VALUE);
        costs.put("H", Integer.MAX_VALUE);
        costs.put("I", Integer.MAX_VALUE);
        costs.put("J", Integer.MAX_VALUE);

        Map<String, String> parents = new Hashtable<>();
        parents.put("B", "A");
        parents.put("C", "A");
        parents.put("D", "A");

        dijkstra(costs, graph, parents);
        System.out.println("costs: " + costs);
        System.out.println("parents: " + parents);

        //Route: AC F EG J
        //Length: 53 km
    }
}
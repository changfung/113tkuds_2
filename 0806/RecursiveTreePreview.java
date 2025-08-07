import java.util.*;

public class RecursiveTreePreview {

    public static void main(String[] args) {
        // 1. 模擬檔案系統並計算檔案數
        Folder root = buildMockFileSystem();
        System.out.println("1. 總檔案數: " + countFiles(root));

        // 2. 列印多層選單結構
        MenuItem menu = buildMockMenu();
        System.out.println("\n2. 多層選單結構:");
        printMenu(menu, 0);

        // 3. 巢狀陣列展平
        List<Object> nestedList = Arrays.asList(1, Arrays.asList(2, 3, Arrays.asList(4, 5)), 6);
        List<Integer> flattened = new ArrayList<>();
        flattenList(nestedList, flattened);
        System.out.println("\n3. 展平巢狀陣列: " + flattened);

        // 4. 計算最大深度
        int depth = maxDepth(nestedList);
        System.out.println("\n4. 巢狀清單最大深度: " + depth);
    }

    // ===== 1. 模擬檔案系統 =====
    static class Folder {
        String name;
        List<Folder> subFolders = new ArrayList<>();
        List<String> files = new ArrayList<>();

        Folder(String name) {
            this.name = name;
        }

        void addFile(String file) {
            files.add(file);
        }

        void addFolder(Folder folder) {
            subFolders.add(folder);
        }
    }

    static Folder buildMockFileSystem() {
        Folder root = new Folder("root");
        root.addFile("a.txt");
        root.addFile("b.txt");

        Folder sub1 = new Folder("sub1");
        sub1.addFile("c.txt");

        Folder sub2 = new Folder("sub2");
        sub2.addFile("d.txt");
        sub2.addFile("e.txt");

        Folder subsub = new Folder("subsub");
        subsub.addFile("f.txt");

        sub2.addFolder(subsub);
        root.addFolder(sub1);
        root.addFolder(sub2);

        return root;
    }

    static int countFiles(Folder folder) {
        int count = folder.files.size();
        for (Folder sub : folder.subFolders) {
            count += countFiles(sub);
        }
        return count;
    }

    // ===== 2. 多層選單列印 =====
    static class MenuItem {
        String name;
        List<MenuItem> children = new ArrayList<>();

        MenuItem(String name) {
            this.name = name;
        }

        void addChild(MenuItem child) {
            children.add(child);
        }
    }

    static MenuItem buildMockMenu() {
        MenuItem root = new MenuItem("主選單");
        MenuItem file = new MenuItem("檔案");
        file.addChild(new MenuItem("新增"));
        file.addChild(new MenuItem("開啟"));

        MenuItem edit = new MenuItem("編輯");
        edit.addChild(new MenuItem("剪下"));
        edit.addChild(new MenuItem("貼上"));

        root.addChild(file);
        root.addChild(edit);
        return root;
    }

    static void printMenu(MenuItem menu, int indent) {
        for (int i = 0; i < indent; i++) System.out.print("  ");
        System.out.println("- " + menu.name);
        for (MenuItem child : menu.children) {
            printMenu(child, indent + 1);
        }
    }

    // ===== 3. 巢狀陣列展平 =====
    @SuppressWarnings("unchecked")
    static void flattenList(List<Object> nested, List<Integer> result) {
        for (Object obj : nested) {
            if (obj instanceof Integer) {
                result.add((Integer) obj);
            } else if (obj instanceof List) {
                flattenList((List<Object>) obj, result);
            }
        }
    }

    // ===== 4. 巢狀清單最大深度 =====
    @SuppressWarnings("unchecked")
    static int maxDepth(List<Object> nested) {
        int max = 1;
        for (Object obj : nested) {
            if (obj instanceof List) {
                max = Math.max(max, 1 + maxDepth((List<Object>) obj));
            }
        }
        return max;
    }
}


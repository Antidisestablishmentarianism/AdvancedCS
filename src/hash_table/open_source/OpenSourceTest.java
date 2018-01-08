package hash_table.open_source;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Saif on 1/4/2018.
 */
public class OpenSourceTest {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("text_files/open_source/open.dat"));

        HashMap<String, HashSet<String>> projects = new HashMap<>();
        HashSet<String> global = new HashSet<>();

        String currProj = "DEFAULT";

        while (in.hasNext()) {
            String line = in.nextLine();

            if (line.equals("0")) break;

            if (line.equals("1")) {
                // TODO: sort and print out previous entries

                ArrayList<String> projList = new ArrayList<>();
                projList.addAll(projects.keySet());

                Collections.sort(projList, new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        HashSet<String> names1 = projects.get(o1);
                        HashSet<String> names2 = projects.get(o2);

                        int size1 = names1.size();
                        int size2 = names2.size();

                        if (size1 == size2) {
                            return o2.compareTo(o1);
                        } else {
                            return size2 - size1 > 0 ? 1 : -1;
                        }
                    }
                });

                for (String s : projList) {
                    System.out.printf("%s %d\n", s, projects.get(s).size());
                }

                projects.clear();
                global.clear();
                continue;
            }

            if (Character.isUpperCase(line.charAt(0))) {
                currProj = line;
                projects.put(line, new HashSet<>());
                continue;
            }

            if (Character.isLowerCase(line.charAt(0))) {
                if (projects.get(currProj).contains(line))
                    continue;

                if (global.contains(line)) {
                    Iterator projectIt = projects.entrySet().iterator();

                    while (projectIt.hasNext()) {
                        Map.Entry names = (Map.Entry) projectIt.next();

                        if (names.getValue().equals(line)) {
                            projectIt.remove();
                        }
                    }

                    continue;
                }

                projects.get(currProj).add(line);
                global.add(line);
                continue;
            }
        }
    }
}

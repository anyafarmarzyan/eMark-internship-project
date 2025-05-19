import java.util.ArrayList;
import java.util.List;

public class datamatrix {

    public static void main(String[] args) {
        List<String> datamatrixList = List.of(
                "010485004072004021+e%'tQp\\u001d93d+9/\n",
                "010485004072034721u9<=83S\\u001d93LSO0\n",
                "010485004072006421r%eZdLD\\u001d93QPJx\n",
                "0104850040720378211Ix5lqx\\u001d93B0+P\n",
                "010485004072013221\"vg0Z%8\\u001d93XZMm\n",
                "010485004072005721AOnf1\"\"\\u001d93mVvG\n",
                "010485004072006421LV<:BEY\\u001d93FXx5\n",
                "010485004072012521%oAqPMw\\u001d93y2y8\n"
        );

        List<Integer> productGroupIds = List.of(5, 3, 8, 22, 7, 9, 1, 2);

        List<String> trimmedList = trimAllByProductGroups(datamatrixList, productGroupIds);

        for (int i = 0; i < trimmedList.size(); i++) {
            System.out.println("Original: " + datamatrixList.get(i));
            System.out.println("Trimmed:  " + trimmedList.get(i));
            System.out.println("----------");
        }
    }

    public static List<String> trimAllByProductGroups(List<String> datamatrixList, List<Integer> productGroupIds) {
        List<String> result = new ArrayList<>();

        for (int i = 0; i < datamatrixList.size(); i++) {
            String datamatrix = datamatrixList.get(i);
            int productGroupId = productGroupIds.get(i);

            result.add(trimByProductGroup(datamatrix, productGroupId));
        }

        return result;
    }

    public static String trimByProductGroup(String datamatrix, int productGroupId) {
        if (datamatrix == null || datamatrix.isEmpty()) {
            return datamatrix;
        }

        int trimLength = getTrimLength(productGroupId);

        if (trimLength <= 0 || datamatrix.length() <= trimLength) {
            return "";
        }

        return datamatrix.substring(0, datamatrix.length() - trimLength);
    }

    private static int getTrimLength(int productGroupId) {
        switch (productGroupId) {
            case 1:
            case 2:
            case 10:
                return 8;
            case 3:
            case 8:
            case 22:
                return 6;
            case 4:
            case 6:
            case 14:
            case 15:
            case 19:
            case 23:
                return 10;
            case 5:
                return 5;
            case 7:
            case 9:
                return 7;
            case 11:
            case 16:
            case 12:
                return 11;
            case 13:
            case 18:
            case 17:
            case 21:
                return 9;
            case 20:
                return 19;
            case 24:
                return 14;
            default:
                return 0;
        }
    }
}

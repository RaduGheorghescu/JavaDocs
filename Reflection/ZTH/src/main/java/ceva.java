import ro.teamnet.zth.api.em.EntityUtils;
import ro.teamnet.zth.appl.domain.Department;

/**
 * Created by Radu.Gheorghescu on 7/12/2017.
 */
public class ceva {
    public static void main(String[] args) throws NoSuchFieldException {
        EntityUtils.getTableName(Department.class);
    }
}

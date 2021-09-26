

@FilterDef(name="dateFilter", parameters = {
        // User input
        @ParamDef(name = "availableStartDate", type = "java.sql.Timestamp"),
        @ParamDef(name = "availableEndDate", type = "java.sql.Timestamp")

})

package entityDO;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
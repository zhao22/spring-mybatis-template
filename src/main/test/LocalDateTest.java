import org.junit.Assert;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 * @author : zhaoxin
 * @date : 2019/7/12.
 */
public class LocalDateTest {

    @Test
    public void test() {
        // 当前时间
        Assert.assertEquals(new SimpleDateFormat("yyyy-MM-dd").format(new Date()), LocalDate.now().toString());
        // 16年2月14日
        LocalDate localDate = LocalDate.of(2016, 2, 14);
        Assert.assertEquals(localDate.toString(), "2016-02-14");
        // 是否闰年
        Assert.assertEquals(localDate.isLeapYear(), true);
        // 年份
        Assert.assertEquals(localDate.getYear(), 2016);
        // 月份
        Assert.assertEquals(localDate.getMonth().getValue(), 2);
        // 天数
        Assert.assertEquals(localDate.getDayOfMonth(), 14);
        // 月份的长度
        Assert.assertEquals(localDate.lengthOfMonth(), 29);
        // 减去一年
        Assert.assertEquals(localDate.minusYears(1).toString(), "2015-02-14");
        // 时间判断
        Assert.assertEquals(localDate.isBefore(LocalDate.now()), true);
    }
}

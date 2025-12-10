package sa.edu.kau.fcit.cpit252.project;

import org.junit.jupiter.api.Test;
import sa.edu.kau.fcit.cpit252.project.CORE.BalanceManager;
import sa.edu.kau.fcit.cpit252.project.CORE.NoteWriter;
import sa.edu.kau.fcit.cpit252.project.CORE.DailyRecord;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;


public class AppTest {

    @Test
    public void testBalanceManagerSingleton() {
        BalanceManager b1 = BalanceManager.getInstance();
        BalanceManager b2 = BalanceManager.getInstance();

        assertSame(b1, b2);
    }

    @Test
    public void testNoteWriterSingleton() {
        NoteWriter n1 = NoteWriter.getInstance();
        NoteWriter n2 = NoteWriter.getInstance();

        assertSame( n1, n2);
    }



        @Test
        public void testDailyRecordBuilder() {

            DailyRecord record = new DailyRecord.Builder()
                    .salary(1200)
                    .bills(200)
                    .food(150)
                    .transportation(50)
                    .entertainment(100)
                    .shopping(90)
                    .savings(300)
                    .build();

            assertEquals(1200, record.getSalary());
            assertEquals(200, record.getBills());
            assertEquals(150, record.getFood());
            assertEquals(50, record.getTransportation());
            assertEquals(100, record.getEntertainment());
            assertEquals(90, record.getShopping());
            assertEquals(300, record.getSavings());
        }
    }



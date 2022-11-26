import com.test.Amazon;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.testng.AssertJUnit.assertEquals;

public class AmazonTest {

    @Test
    void TestTheUrlForTheLearnedPage() throws IOException {
       Amazon check_url = new Amazon();
       String url = check_url.BrowseSmileAmazon();
       assertEquals("https://smile.amazon.com/charity/smile/about?ref_=smi_ge2_ul_lm_uaas", url);
    }
}

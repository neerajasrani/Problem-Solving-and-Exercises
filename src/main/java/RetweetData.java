import java.math.BigInteger;

/**
 * Created by neeraj on 1/9/15.
 */
public class RetweetData {


    private String text;
    private BigInteger count = BigInteger.ZERO;

    public RetweetData(String text, BigInteger count) {
        this.text = text;
        this.count = count;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public BigInteger getCount() {
        return count;
    }

    public void setCount(BigInteger count) {
        this.count = count;
    }
}

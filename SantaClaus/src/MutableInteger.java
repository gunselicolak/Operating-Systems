public class MutableInteger {
    private int value;

    public MutableInteger(int value)
    {
        this.value = value;
    }
    public void setValue(int value)
    {
        this.value = value;
    }
    public int getValue()
    {
        return value;
    }

}

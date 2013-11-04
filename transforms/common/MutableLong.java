package common;

public class MutableLong {
    private long val;
    public MutableLong() {
        this.val = 0;
    }
    public MutableLong(long set) {
        this.val = set;
    }
    public void incr() {
        this.val++;
    }
    public long get() {
        return this.val;
    }
    public void add(MutableLong other) {
        this.val += other.val;
    }
    public void add(long l) {
        this.val += l;
    }
}

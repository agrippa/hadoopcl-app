package common;

public class TokenCount implements Comparable<TokenCount> {
    private final int token;
    private final long count;
    public TokenCount(int token, long count) {
        this.token = token; this.count = count;
    }
    public int token() { return this.token; }
    public long count() { return this.count; }
    @Override
    public int compareTo(TokenCount other) {
        if (this.count < other.count) {
            return -1;
        } else if(this.count > other.count) {
            return 1;
        } else {
            if (this.token < other.token) {
                return -1;
            } else if (this.token > other.token) {
                return 1;
            } else {
                return 0;
            }
        }
    }
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof TokenCount) {
            TokenCount other = (TokenCount)obj;
            return this.token == other.token && this.count == other.count;
        }
        return false;
    }
    @Override
    public int hashCode() {
        return this.token;
    }

}

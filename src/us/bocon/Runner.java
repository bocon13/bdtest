package us.bocon;

import java.io.Serializable;

public class Runner {
    public static void main(String[] args) {
        System.out.println("This is a blackduck test");
    }

  //https://github.com/google/guava/blob/v25.0/guava/src/com/google/common/base/Strings.java#L88
  public static String padStart(String string, int minLength, char padChar) {
    checkNotNull(string); // eager for GWT.
    if (string.length() >= minLength) {
      return string;
    }
    StringBuilder sb = new StringBuilder(minLength);
    for (int i = string.length(); i < minLength; i++) {
      sb.append(padChar);
    }
    sb.append(string);
    return sb.toString();
  }

  //https://github.com/google/guava/blob/v25.0/guava/src/com/google/common/hash/HashCode.java#L120
  private static final class IntHashCode extends HashCode implements Serializable {
    final int hash;

    IntHashCode(int hash) {
      this.hash = hash;
    }

    @Override
    public int bits() {
      return 32;
    }

    @Override
    public byte[] asBytes() {
      return new byte[] {(byte) hash, (byte) (hash >> 8), (byte) (hash >> 16), (byte) (hash >> 24)};
    }

    @Override
    public int asInt() {
      return hash;
    }

    @Override
    public long asLong() {
      throw new IllegalStateException("this HashCode only has 32 bits; cannot create a long");
    }

    @Override
    public long padToLong() {
      return UnsignedInts.toLong(hash);
    }

    @Override
    void writeBytesToImpl(byte[] dest, int offset, int maxLength) {
      for (int i = 0; i < maxLength; i++) {
        dest[offset + i] = (byte) (hash >> (i * 8));
      }
    }

    @Override
    boolean equalsSameBits(HashCode that) {
      return hash == that.asInt();
    }

    private static final long serialVersionUID = 0;
  }
}

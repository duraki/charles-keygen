/* Charles Proxy Keygen */
/* Cracked on 27042020 by @0xduraki via RC5 dec. */

/** usage: */
//$ javac CharlesKeygen.java && java CharlesKeygen "0XDURAKI" 

// @see: https://github.com/duraki/charles-keygen
// @see: https://twitter.com/0xduraki
class CharlesKeygen {
    public static void main(String[] args) {
				if (args.length == 1) {
					String name = args[0];
					System.out.print("* GENERATED LICENSE:\n  [" + name + ":" + calcLicenseKey(name) + "]\n");

					return;
				}

				System.out.print("Charles Proxy - License Key Generator [ALL VERSIONS]\n");
				System.out.print("Proudly presented by >> Team 0xCHAOS\n");
				System.out.print("**"+"Usage:\n");
				System.out.print("$ CharlesKeygen \"TEAM0XCHAOS\"\n\n");

				if (args.length != 1) {
					System.out.print("Error: Missing License Name ...\n");
					return;
				}
    }

		public static String calcLicenseKey(String licenseName) {
			int licenseKeyChecksum = 1418211210;
			int licenseNameChecksum = licenseNameChecksum(licenseName);

    	licenseKeyChecksum ^= licenseNameChecksum;
			long bLicenseKey = licenseKeyChecksum;
			bLicenseKey <<= 32L;
			bLicenseKey >>>= 32L;
			bLicenseKey <<= 32L;
			bLicenseKey |= 0x1CAD6BCL;
			
			int keyEncMin = (int)(bLicenseKey & 0xFFFFFFFFFFFFFFFFL);
			int keyEncMax = (int)(bLicenseKey >>> 32L & 0xFFFFFFFFFFFFFFFFL);

			int[] keyEnc = new int[2];
			keyEnc[0] = keyEncMin;
			keyEnc[1] = keyEncMax;

			int[] keyDec = new int[2];
			RC5 decrypter = new RC5();
			decrypter.RC5_SETUP(-334581843, -1259282228);
			decrypter.RC5_DECRYPT(keyEnc, keyDec);
			
			long keyDecrypted = (keyDec[1] & 0xFFFFFFFFL) << 32L;
			keyDecrypted |= keyDec[0] & 0xFFFFFFFFL;
		
			int xorChecksum = xorChecksum(bLicenseKey);
			String finalKey = Integer.toHexString(xorChecksum) + Long.toHexString(keyDecrypted);

			finalKey = String.format("%02X", new Object[] { Integer.valueOf(xorChecksum) }) + String.format("%016X", new Object[] { Long.valueOf(keyDecrypted) });
			return finalKey;				
		}

		public static int licenseNameChecksum(String licenseName) {
				byte[] bArrayName = null;

				try {
					bArrayName = licenseName.getBytes("UTF-8");
				} catch (Exception e) {
					System.out.println(e.toString());
				}

				int nameLen = bArrayName.length;
				int n = nameLen + 4;

				if (n % 8 != 0)
					n += 8 - n % 8;
				byte[] arrbChecksum = new byte[n];
				System.arraycopy(bArrayName, 0, arrbChecksum, 4, nameLen);
				arrbChecksum[0] = (byte)(nameLen >> 24);
				arrbChecksum[1] = (byte)(nameLen >> 16);
				arrbChecksum[2] = (byte)(nameLen >> 8);
				arrbChecksum[3] = (byte)nameLen;
				RC5 r = new RC5();
				r.RC5_SETUP(1763497072, 2049034577);
				byte[] outputArray = r.RC5_EncryptArray(arrbChecksum);
				int n3 = 0;
				for (byte by : outputArray) {
					n3 ^= by;
					n3 = n3 << 3 | n3 >>> 29;
				} 
				return n3;
		}

		private static final int xorChecksum(long l) {
			long l2 = 0L;
			for (int i = 56; i >= 0; i -= 8)
				l2 ^= l >>> i & 0xFFL; 

			return Math.abs((int)(l2 & 0xFFL));
		}

		public static byte[] hexToByteArray(String hexstring) {
			int len = hexstring.length();
			byte[] data = new byte[len / 2];

			for (int i = 0; i < len; i += 2)
				data[i / 2] = (byte)((Character.digit(hexstring.charAt(i), 16) << 4) + Character.digit(hexstring.charAt(i + 1), 16));

			return data;
		}
}

class RC5 {
  public static final int w = 32;
  
  public static final int r = 12;
  
  public static final int b = 8;
  
  public static final int c = 2;
  
  public static final int t = 26;
  
  public int[] S = new int[26];
  
  public int P = -1209970333;
  
  public int Q = -1640531527;
  
  public byte[] RC5_DecryptArray(byte[] arrby) {
    byte[] arrby2 = new byte[arrby.length];
    int n = arrby.length;
    int n2 = 0;
    long l = 0L;
    int l1 = 0, l2 = 0;
    for (int i = 0; i < n; i++) {
      if (l < 4L) {
        l1 <<= 8;
        l1 |= arrby[i] & 0xFF;
      } else {
        l2 <<= 8;
        l2 |= arrby[i] & 0xFF;
      } 
      l++;
      if (++n2 == 8) {
        int[] ct = { l2, l1 };
        int[] pt = { 0, 0 };
        RC5_DECRYPT(ct, pt);
        arrby2[i - 7] = (byte)(pt[1] >>> 24);
        arrby2[i - 6] = (byte)(pt[1] >>> 16);
        arrby2[i - 5] = (byte)(pt[1] >>> 8);
        arrby2[i - 4] = (byte)pt[1];
        arrby2[i - 3] = (byte)(pt[0] >>> 24);
        arrby2[i - 2] = (byte)(pt[0] >>> 16);
        arrby2[i - 1] = (byte)(pt[0] >>> 8);
        arrby2[i] = (byte)pt[0];
        n2 = 0;
        l = 0L;
        l1 = 0;
        l2 = 0;
      } 
    } 
    return arrby2;
  }
  
  public byte[] RC5_EncryptArray(byte[] arrby) {
    byte[] arrby2 = new byte[arrby.length];
    int n = arrby.length;
    int n2 = 0;
    long l = 0L;
    int l1 = 0, l2 = 0;
    for (int i = 0; i < n; i++) {
      if (l < 4L) {
        l1 <<= 8;
        l1 |= arrby[i] & 0xFF;
      } else {
        l2 <<= 8;
        l2 |= arrby[i] & 0xFF;
      } 
      l++;
      if (++n2 == 8) {
        int[] pt = { l2, l1 };
        int[] ct = { 0, 0 };
        RC5_ENCRYPT(pt, ct);
        arrby2[i - 7] = (byte)(ct[1] >>> 24);
        arrby2[i - 6] = (byte)(ct[1] >>> 16);
        arrby2[i - 5] = (byte)(ct[1] >>> 8);
        arrby2[i - 4] = (byte)ct[1];
        arrby2[i - 3] = (byte)(ct[0] >>> 24);
        arrby2[i - 2] = (byte)(ct[0] >>> 16);
        arrby2[i - 1] = (byte)(ct[0] >>> 8);
        arrby2[i] = (byte)ct[0];
        n2 = 0;
        l = 0L;
        l1 = 0;
        l2 = 0;
      } 
    } 
    return arrby2;
  }
  
  void RC5_ENCRYPT(int[] pt, int[] ct) {
    int A = pt[0] + this.S[0];
    int B = pt[1] + this.S[1];
    for (int i = 1; i <= 12; i++) {
      A = ((A ^ B) << (B & 0x1F) | (A ^ B) >>> 32 - (B & 0x1F)) + this.S[2 * i];
      B = ((B ^ A) << (A & 0x1F) | (B ^ A) >>> 32 - (A & 0x1F)) + this.S[2 * i + 1];
    } 
    ct[0] = A;
    ct[1] = B;
  }
  
  void RC5_DECRYPT(int[] ct, int[] pt) {
    int B = ct[1];
    int A = ct[0];
    for (int i = 12; i > 0; i--) {
      B = (B - this.S[2 * i + 1] >>> (A & 0x1F) | B - this.S[2 * i + 1] << 32 - (A & 0x1F)) ^ A;
      A = (A - this.S[2 * i] >>> (B & 0x1F) | A - this.S[2 * i] << 32 - (B & 0x1F)) ^ B;
    } 
    pt[1] = B - this.S[1];
    pt[0] = A - this.S[0];
  }
  
  void RC5_SETUP(int l, int h) {
    int u = 4;
    int[] L = new int[2];
    L[0] = l;
    L[1] = h;
    int i;
    for (this.S[0] = this.P, i = 1; i < 26; i++)
      this.S[i] = this.S[i - 1] + this.Q; 
    int j;
    for (int k = 0, B = i = j = k = 0, A = B; k < 78; k++, i = (i + 1) % 26, j = (j + 1) % 2) {
      A = this.S[i] = this.S[i] + A + B << 3 | this.S[i] + A + B >>> 29;
      B = L[j] = L[j] + A + B << (A + B & 0x1F) | L[j] + A + B >>> 32 - (A + B & 0x1F);
    } 
    System.out.println();
  }
  
  public static String hex(int n) {
    return String.format("0x%s", new Object[] { Integer.toHexString(n) }).replace(' ', '0');
  }
}


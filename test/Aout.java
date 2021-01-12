
/** Helper methods.
 *   Arrays converted to array expressions (toString(...)) 
 *   and prettyprinting (toPrettyString(...)).
 * @author Jaanus
 */
public class Aout {

   /** Just testing. */
   public static void main (String[] args) {
      System.out.println (toString (new String[]
         {null, "", "0", "a\nb", " ", " 	", "\t", "möäüÕga", "\"", "\\", "\'"}));
   } // main

   /** Conversion for int[][] . */
   public static String toString (int[][] m) {
      if (m == null) return "(int[][])null";
      StringBuffer sb = new StringBuffer();
      sb.append ("new int[][]{");
      for (int i=0; i < m.length; i++) {
         if (i > 0) sb.append (", ");
         if (m[i] == null)
            sb.append ("null"); 
         else {
            sb.append ("{");
            for (int j=0; j < m[i].length; j++) {
               if (j > 0) sb.append (", ");
               sb.append (String.valueOf (m[i][j])); 
            } // for j
            sb.append ("}");
         }
      } // for i
      sb.append ("}");
      return sb.toString();
   } // toString int[][]

   /** Conversion for double[][] . */
   public static String toString (double[][] m) {
      if (m == null) return "(double[][])null";
      StringBuffer sb = new StringBuffer();
      sb.append ("new double[][]{");
      for (int i=0; i < m.length; i++) {
         if (i > 0) sb.append (", ");
         if (m[i] == null)
            sb.append ("null");
         else {
            sb.append ("{");
            for (int j=0; j < m[i].length; j++) {
               if (j > 0) sb.append (", ");
               sb.append (String.valueOf (m[i][j]));
            } // for j
            sb.append ("}");
         }
      } // for i
      sb.append ("}");
      return sb.toString();
   } // toString double[][]

   /** Conversion for int[] . */
   public static String toString (int[] m) {
      if (m == null) return "(int[])null";
      StringBuffer sb = new StringBuffer();
      sb.append ("new int[]{");
      for (int i=0; i < m.length; i++) {
         if (i > 0) sb.append (", ");
         sb.append (String.valueOf (m[i]));
      } // for i
      sb.append ("}");
      return sb.toString();
   } // toString int[]

   /** Conversion for double[] . */
   public static String toString (double[] m) {
      if (m == null) return "(double[])null";
      StringBuffer sb = new StringBuffer();
      sb.append ("new double[]{");
      for (int i=0; i < m.length; i++) {
         if (i > 0) sb.append (", ");
         sb.append (String.valueOf (m[i]));
      } // for i
      sb.append ("}");
      return sb.toString();
   } // toString double[]

   /** Conversion for int . */
   public static String toString (int n) {
      return String.valueOf (n);
   } // toString int

   /** Conversion for double . */
   public static String toString (double d) {
      return String.valueOf (d);
   } // toString double

   /** Conversion for String . */
   public static String toString (String s) {
      if (s == null) 
         return "null";
      StringBuffer tmp = new StringBuffer();
      for (int k=0; k < s.length(); k++) {
         char c = s.charAt (k);
         switch (c) {
            case '\n': { tmp.append ("\\n"); break; }
            case '\t': { tmp.append ("\\t"); break; }
            case '\b': { tmp.append ("\\b"); break; }
            case '\f': { tmp.append ("\\f"); break; }
            case '\r': { tmp.append ("\\r"); break; }
            case '\\': { tmp.append ("\\\\"); break; }
            case '\'': { tmp.append ("\\\'"); break; }
            case '\"': { tmp.append ("\\\""); break; }
            // TODO!!! add more escapes if needed
            default: tmp.append (c);
         } // switch
      } // for k
      return "\"" + tmp.toString() + "\"";
   } // toString String

   /** Conversion for String[] . */
   public static String toString (String[] m) {
      if (m == null) 
         return "(String[])null";
      StringBuffer sb = new StringBuffer();
      sb.append ("new String[]{");
      for (int i=0; i < m.length; i++) {
         if (i > 0) 
            sb.append (", ");
         sb.append (toString (m[i]));
      } // for i
      sb.append ("}");
      return sb.toString();
   } // toString String[]

   /** Double number as string with the given length.
    * @param d argument
    * @param len length
    * @return d as string
    */
   public static String fString (double d, int len) {
      if (len<1) 
         return "";
      // pad on ruum punkti ja v6imaliku miinusm2rgi jaoks
      int pad = 1 + ((d<0)?1:0);
      // loga on t2isosa numbrikohtade arv
      int loga = (int)Math.max (0., Math.log10 (Math.abs (d))) + 1;
      // kk on punkti j2rel olevate kohtade arv
      int kk = (int)Math.max (len-pad-loga, 0);
      String fs = "%" + String.valueOf (len) + "." + 
         String.valueOf (kk) + "f";
      String res = "";
      try {
         res = String.format ((java.util.Locale)null, fs, d);
      } catch (IllegalArgumentException e) {
         res = String.valueOf (d);
      } // try
      return res;
   } // fString

   /** Prettyprint for double[][] . 
    * @param m array to print
    * @param fs format string for element
    * @return m array as multiline string
    */
   public static String toPrettyString (double[][] m, String fs) {
      String nl = System.getProperty ("line.separator");
      if (m == null)
         return "nullpointer instead of this matrix" + nl;
         // throw new NullPointerException ("(double[][])null"); // alternative
      if (m.length == 0)
         return "this matrix is empty" + nl;
      StringBuffer sb = new StringBuffer(nl);
      for (int i=0; i < m.length; i++) {
         if (m[i] == null)
            sb.append ("nullpointer instead of this row" + nl);
         else {
            if (m[i].length == 0)
               sb.append ("this row is empty");
            else {
               for (int j=0; j < m[i].length; j++) {
                  String elem = "";
                  if (fs == null || fs.length() < 1) {
                     // TODO!!! keera siit, kui tahad pilti muuta
                     elem = fString (m[i][j], 6) + "\t";
                  } else {
                     try {
                        elem = String.format ((java.util.Locale)null,
                           fs, m[i][j]) + " "; // remove space if needed
                     } catch (IllegalArgumentException e) {
                        elem = fString (m[i][j], 6) + "\t";
                     } // try
                  }
                  sb.append (elem);
               } // for j
            } // nonempty row
            sb.append (nl);
         } // non-null row
      } // for i
      return sb.toString();
   } // toPrettyString double[][]

   /** Version of double[][] prettyprint without format string. */
   public static String toPrettyString (double[][] m) {
      return toPrettyString (m, null);
   } // toPrettyString double[][]

   /** Prettyprint for int[][] .
    * @param m array to print
    * @param fs format string for element
    * @return m array as a multiline string
    */
   public static String toPrettyString (int[][] m, String fs) {
      String nl = System.getProperty ("line.separator");
      if (m == null)
         return "nullpointer instead of this matrix" + nl;
         // throw new NullPointerException ("(double[][])null"); // alternative
      if (m.length == 0)
         return "this matrix is empty" + nl;
      StringBuffer sb = new StringBuffer(nl);
      for (int i=0; i < m.length; i++) {
         if (m[i] == null)
            sb.append ("nullpointer instead of this row" + nl);
         else {
            if (m[i].length == 0)
               sb.append ("this row is empty");
            else {
               for (int j=0; j < m[i].length; j++) {
                  String elem = "";
                  if (fs == null || fs.length() < 1)
                     fs = "%5d";  // TODO!!! keera siit, kui vaja
                  try {
                     elem = String.format ((java.util.Locale)null,
                        fs, m[i][j]) + " "; // remove space if needed
                  } catch (IllegalArgumentException e) {
                     elem = String.valueOf (m[i][j]) + "\t";
                  } // try
                  sb.append (elem);
               } // for j
            } // nonempty row
            sb.append (nl);
         } // non-null row
      } // for i
      return sb.toString();
   } // toPrettyString int[][]

   /** Version of int[][] prettyprint without format string. */
   public static String toPrettyString (int[][] m) {
      return toPrettyString (m, null);
   } // toPrettyString int[][]

   /** Prettyprint for String[] . */
   public static String toPrettyString (String[] m) {
      String nl = System.getProperty ("line.separator");
      if (m == null) return "(String[])null";
      StringBuffer sb = new StringBuffer();
      for (int i=0; i < m.length; i++) {
         sb.append (toString (m[i]) + nl);
      } // for i
      return sb.toString();
   } // toPrettyString String[]

} // Aout


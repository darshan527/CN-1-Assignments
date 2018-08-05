import java.util.*;

class CRC{
    public static void main(String[] args){
        int data_len,gen_len;
        Scanner sc = new Scanner(System.in);
               
        // Read data bits
        System.out.println("Enter size of data");
        data_len = sc.nextInt();
        
        System.out.println("Enter data bits");
        int[] data = new int[data_len];
        
        for(int i=0;i<data_len;i++)
            data[i] = sc.nextInt();
       
        //Read Generator
        System.out.println("Enter size of generator");
        gen_len = sc.nextInt();
        
        System.out.println("Enter data bits");
        int[] gen = new int[gen_len];
        
        for(int i=0;i<gen_len;i++)
            gen[i] = sc.nextInt();
        
        int tot_len = data_len+gen_len-1;
        
        int[] div = new int[tot_len];
        int[] rem = new int[tot_len];
        int[] crc = new int[tot_len];
        
        //System.out.println("Data after appending zero");
        System.arraycopy(data, 0, div, 0, data_len);
        
        System.arraycopy(div, 0, rem, 0, tot_len);
        
        //Calculate CRC
        crc = divide(gen,div,rem);
        
        //append remainder to data
        for(int i=0;i<data_len;i++)
            crc[i] = (crc[i]^data[i]);
        
        System.out.println("Generated CRC");
        for(int i=0;i<tot_len;i++)
            System.out.print(crc[i] + " ");
        
        //CRC checker
        System.out.println("\n\nEnter "+tot_len+"bit CRC to verify");
        int[] ver = new int[tot_len];
        
        for(int i=0;i<tot_len;i++)
            ver[i]= sc.nextInt();
        
        
        System.arraycopy(ver, 0, rem, 0, tot_len);
        rem = divide(gen,ver,rem);
        
        for(int i=0; i<=rem.length;i++){
            if(rem[i]!=0){
                System.out.println("Error in transmission");
                break;
            }
            if(i == rem.length-1){
                System.out.println("No Error");
                break;
            }
        }
        
    }
    //Divide method
    static int[] divide(int[] gen,int[] data,int[] rem){
        int cur=0;
        while(true){
            for(int i=0;i<gen.length;i++)
                rem[cur+i] = (gen[i]^rem[cur+i]);
            
            while(rem[cur]==0 && cur!=rem.length-1)
                cur++;
            
            if((rem.length-cur)<gen.length)
                break;
        }
        return rem;
    }
}
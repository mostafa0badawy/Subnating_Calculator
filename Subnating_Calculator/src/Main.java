import java.util.Scanner;

public class Main  {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        try {
            // Get the IPv4 address from the user
            System.out.print("Enter an IPv4 address: ");
            String ipAddressInput = in.nextLine();

            // Input: Number of needed hosts
            System.out.print("Enter a number of needed hosts= ");
            int numberOfHosts = in.nextInt();

            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

            // Create an IPAddress object
            IPAddress ipAddress = new IPAddress(ipAddressInput);

            // Print the class type
            String classType = ipAddress.getClassType();
            System.out.println("The IP address class is: " + classType);



            // Create a SubnetCalculator object
            SubnetCalculator subnetCalculator = new SubnetCalculator(numberOfHosts);


            // Find the closest power of 2 that can accommodate the needed hosts
            int power = (int) Math.ceil(Math.log(numberOfHosts) / Math.log(2));
            int closestPowerOfTwo = (int) Math.pow(2, power);

            System.out.println("The closest power of 2 for the number " + numberOfHosts + " is: 2^" + power);
            System.out.println("Number of hosts (including network/broadcast): " + closestPowerOfTwo);


            // Calculate the new subnet prefix length
            int subnetPrefixLength = subnetCalculator.calculateSubnetPrefixLength();
            System.out.println("The subnet prefix length (slash notation) is= /" + subnetPrefixLength);

            // Calculate and display the subnet mask in binary
            String subnetMaskBinary = subnetCalculator.calculateSubnetMaskBinary();
            System.out.println("The subnet mask in binary is= " + subnetMaskBinary);

            // Calculate and display the subnet mask in decimal
            String subnetMaskDecimal = subnetCalculator.calculateSubnetMaskDecimal();
            System.out.println("The subnet mask in decimal is = " + subnetMaskDecimal);

            System.out.println("The number of networks that will be available from this ip= "+ (closestPowerOfTwo - 256));

        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        in.close();
    }
}
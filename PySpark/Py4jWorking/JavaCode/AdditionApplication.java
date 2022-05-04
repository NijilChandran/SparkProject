import py4j.GatewayServer;

public class AdditionApplication{

public int addition(int firstnum,int SecondNum){
	return firstnum+SecondNum;
}

public static void main(String[] args){

  AdditionApplication app = new AdditionApplication();
  GatewayServer server = new GatewayServer(app);
  server.start();

}
}
import java.util.Random;

class Bus extends public_transport{

    final String number;
    String statestr="운행";

    @Override
    public void change_state(){
        super.change_state();
        statestr=state?"운행":"차고지행";
    };
    @Override
    void start_service() {
        Random random = new Random();
        System.out.println("운행을 시작합니다.");
        System.out.println("다음정거장으로 이동합니다.");
        change_speed(40);

        while(state==true){
            System.out.println("이동중");
            change_speed(-40);
            System.out.println("정류장 도착했습니다.");
            pass_boarding(random.nextInt(10));
            juu-= random.nextInt(5)+10;
            System.out.println("다음정거장으로 이동합니다.");
            System.out.println("------------------");
            change_speed(40);
        }
    }

    public Bus(int maxpas, int cost, String number, int juu) {
        this.maxpas = maxpas;
        this.cost = cost;
        this.number = number;
        this.juu = juu;
        this.state = true;
    }
}

class Taxi extends public_transport{
    Random random = new Random();
    String destination;
    int distance= 3000,dis_to_des,dis_cost;

    final String number;
    String statestr ="일반";   // 승객을 태우면 운행중 승객이 없을때 일반

    @Override
    void start_service() {
        if(juu<10){return;}
        System.out.println("택시 운행을 시작합니다. 현재 운행 상태는 \"" + statestr+"\"입니다.");
        System.out.println("해당 택시의 번호는 \""+number+"\", 현재 주유량은 " +juu+"%입니다.");
        change_speed(80);
        System.out.println("현재 운행 상태는 \""+ statestr+"\"입니다.");
        System.out.println("-----------------");

        for (int i = 0; i < 3; i++) {
            change_speed(-80);
            pass_boarding(random.nextInt(3)+1);
            setDestination((i+1)+"번째 집",random.nextInt(6000));
            change_speed(80);
            System.out.println("현재 운행 상태는 \""+ statestr+"\"입니다.");
            addcost_by_dis();
            System.out.println("목적지에 도착했습니다.");
            change_speed(-80);
            finalcost();
            pass_drop();
            change_speed(80);
            System.out.println("현재 운행 상태는 \""+ statestr+"\"입니다.");

            System.out.println("-----------------");
        }
    }

    @Override
    void pass_boarding(int addpas) {
        super.pass_boarding(addpas);
        change_state();
    }; // 승객탑승 메소드
    void pass_drop(){
        System.out.println("승객이 내렸습니다.");
        nowpas =0;
        change_state();
    } // 승객 하차 메소드

    @Override
    public void change_state(){
        super.change_state();
        statestr=state?"일반": "운행중";
    }   //상태변경 메소드

    public void addcost_by_dis(){cost+=((dis_to_des -distance)/100)*2;} // 추가비용 메소드
    public void finalcost(){System.out.println("총 결제 가격은 "+cost +"원 입니다.");} //요금 결제 메소드
    public void setDestination(String destination, int dis_to_des){
        this.destination = destination;
        this.dis_to_des = dis_to_des;
        System.out.println("이번 목적지는 "+destination+ "이며 거기까지 거리는 " + dis_to_des+"m 입니다.");
    }// 목적지 설정 메소드

    public Taxi(int maxpas, int cost, int dis_cost, String number, int juu) {
        this.maxpas = maxpas;
        this.cost = cost;
        this.dis_cost = dis_cost;
        this.number = number;
        this.juu = juu;
    }   //생성자
}

public class Main {
    public static void main(String[] args) {
        Bus bus = new Bus(50, 1200, "1234나12", 80);
        Taxi taxi = new Taxi(5,6500,200,"1234고03",90);
        taxi.start_service();
    }
}
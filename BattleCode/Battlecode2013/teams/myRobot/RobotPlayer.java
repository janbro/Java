package myRobot; 
  
import battlecode.common.Direction; 
import battlecode.common.GameActionException; 
import battlecode.common.MapLocation; 
import battlecode.common.Robot; 
import battlecode.common.RobotController; 
import battlecode.common.RobotInfo; 
import battlecode.common.RobotType; 
import battlecode.common.Team; 
import battlecode.common.Upgrade; 
  
public class RobotPlayer { 
  
    static RobotController rc; 
    static boolean pattern=true; 
      
    public static void run(RobotController myRC){ 
        rc = myRC; 
          
        while(true){ 
            try{ 
                switch(rc.getType()){ 
                case SOLDIER: 
                    soldierCode(); 
                case HQ: 
                    hqCode(); 
                case ARTILLERY: 
                    artilleryCode(); 
                } 
            }catch (Exception e){ 
                System.out.println("caught exception before it killed us:"); 
                e.printStackTrace(); 
            } 
            rc.yield(); 
        } 
    } 
    private static MapLocation findClosest(Robot[] enemyRobots) throws GameActionException { 
        int closestDist = 1000000; 
        MapLocation closestEnemy=null; 
        for (int i=0;i<enemyRobots.length;i++){ 
            Robot arobot = enemyRobots[i]; 
            RobotInfo arobotInfo = rc.senseRobotInfo(arobot); 
            int dist = arobotInfo.location.distanceSquaredTo(rc.getLocation()); 
            if (dist<closestDist){ 
                closestDist = dist; 
                closestEnemy = arobotInfo.location; 
            } 
        } 
        return closestEnemy; 
    } 
    public static void artilleryCode(){ 
        while(true){ 
            try{ 
                if(rc.isActive()){ 
                    Robot[] enemies = rc.senseNearbyGameObjects(Robot.class,10000000,rc.getTeam().opponent()); 
                    rc.attackSquare(findClosest(enemies)); 
                } 
            } 
            catch (Exception e){ 
                System.out.println("Artillery Exception"); 
                e.printStackTrace(); 
            } 
            rc.yield(); 
        } 
    } 
      
    private static void soldierCode(){ 
        Direction dirToEnemyHQ=rc.getLocation().directionTo(rc.senseEnemyHQLocation()); 
        boolean fallBack=false; 
        if(rc.senseEnemyHQLocation().x-rc.senseHQLocation().x!=0){ 
            if(Math.abs((rc.senseEnemyHQLocation().y-rc.senseHQLocation().y)/(rc.senseEnemyHQLocation().x-rc.senseHQLocation().x))>=1){ 
                pattern=false; 
            } 
        }else{ 
            pattern=false; 
        } 
          
        while(true){ 
            try{ 
                Robot[] allies = rc.senseNearbyGameObjects(Robot.class,14,rc.getTeam()); 
                Robot[] enemies = rc.senseNearbyGameObjects(Robot.class,10000000,rc.getTeam().opponent()); 
                Robot[] nearbyEnemies = rc.senseNearbyGameObjects(Robot.class,14,rc.getTeam().opponent()); 
                int surroundingMines[][]=new int[3][3]; 
                  
                Direction posDirs[] = {Direction.NORTH_WEST,Direction.NORTH,Direction.NORTH_EAST,Direction.WEST,Direction.EAST,Direction.SOUTH_WEST,Direction.SOUTH,Direction.SOUTH_EAST}; 
                Direction surroundings[] = {Direction.NORTH_WEST,Direction.NORTH,Direction.NORTH_EAST,Direction.WEST,Direction.NONE,Direction.EAST,Direction.SOUTH_WEST,Direction.SOUTH,Direction.SOUTH_EAST}; 
                Direction dir = rc.getLocation().directionTo(rc.senseEnemyHQLocation()); 
                int hqDist = (int)(Math.sqrt(Math.pow(rc.senseEnemyHQLocation().x-rc.senseHQLocation().x,2)+Math.pow(rc.senseEnemyHQLocation().y-rc.senseHQLocation().y,2))); 
                  
                for(int j=0;j<posDirs.length;j++){ 
                    if(posDirs[j]==dir.opposite()){ 
                        posDirs[j]=dir; 
                    } 
                } 
                dir = posDirs[(int)(Math.random()*8)];//rc.getLocation().directionTo(rc.senseEnemyHQLocation()); 
                if(Math.sqrt(Math.pow(rc.senseHQLocation().x-rc.getLocation().x,2)+Math.pow(rc.senseHQLocation().y-rc.getLocation().y,2))>hqDist){ 
                    fallBack=true; 
                } 
                if(Math.sqrt(Math.pow(rc.senseHQLocation().x-rc.getLocation().x,2)+Math.pow(rc.senseHQLocation().y-rc.getLocation().y,2))<2){ 
                    fallBack=false; 
                } 
                if(fallBack) 
                    dir=rc.getLocation().directionTo(rc.senseHQLocation()); 
                  
                if(rc.isActive()){ 
                    for(int i=0;i<3;i++){ 
                        for(int j=0;j<3;j++){ 
                            if(rc.senseMine(rc.getLocation().add(surroundings[i*3+j]))!=null){ 
                                surroundingMines[i][j]=1; 
                            } 
                            else{ 
                                surroundingMines[i][j]=0; 
                            } 
                        } 
                    } 
                    int amountOfSoldiers=0; 
  
                    for(Robot r:allies){ 
                        if(r.getClass().equals(RobotType.SOLDIER)){ 
                            amountOfSoldiers++; 
                        } 
                    } 
                    if(rc.senseEncampmentSquare(rc.getLocation())){ 
                        rc.captureEncampment(RobotType.ARTILLERY); 
                    } 
                    else if(nearbyEnemies.length==0){ 
                        int rx,ry; 
                        if(pattern){ 
                            rx=rc.getLocation().x; 
                            ry=rc.getLocation().y; 
                        } 
                        else{ 
                            rx=rc.getLocation().y; 
                            ry=rc.getLocation().x; 
                        } 
                        if((rx+(ry%3))%5==0&&rc.senseMine(rc.getLocation())==null){ 
                            //if(rc.getLocation().x%5==0&&0==rc.getLocation().y%5&&rc.senseMine(rc.getLocation())==null){ 
                            rc.layMine(); 
                        } 
                        else if(rc.senseMine(rc.getLocation().add(dir))==Team.NEUTRAL||rc.senseMine(rc.getLocation().add(dir))==rc.getTeam().opponent()){ 
                            rc.defuseMine(rc.getLocation().add(dir)); 
                        } 
                        else if(rc.canMove(dir)){ 
                            rc.move(checkDir(rc,dir)); 
                        } 
                    } 
                    else{//someone spotted 
                        MapLocation closestEnemy = findClosest(nearbyEnemies); 
                        MapLocation closestAlly = findClosest(allies); 
                        double dist = Math.sqrt(Math.pow(rc.senseEnemyHQLocation().x-rc.getLocation().x,2)+Math.pow(rc.senseEnemyHQLocation().y-rc.getLocation().y,2)); 
                        if(dist<10){ 
                            rc.move(rc.getLocation().directionTo(rc.senseEnemyHQLocation())); 
                        } 
                        if(nearbyEnemies.length+3<amountOfSoldiers){ 
                            rc.move(rc.getLocation().directionTo(closestEnemy)); 
                        } 
                        else{ 
                            rc.move(rc.getLocation().directionTo(closestAlly)); 
                        } 
                    } 
                } 
            }catch (Exception e){ 
                System.out.println("Soldier Exception"); 
                e.printStackTrace(); 
            } 
            rc.yield(); 
        } 
    } 
      
    private static Direction checkDir(RobotController rc, Direction dir){ 
        if(rc.senseNearbyGameObjects(Robot.class, rc.getLocation().add(dir), 5, rc.getTeam().opponent()).length>rc.senseNearbyGameObjects(Robot.class, rc.getLocation().add(dir), 5, rc.getTeam()).length){ 
            return dir.opposite(); 
        } 
        return dir; 
    } 
      
    private static void hqCode(){ 
        MapLocation myLoc = rc.getLocation(); 
        MapLocation enemyLoc = rc.senseEnemyHQLocation(); 
        Direction surroundings[] = {Direction.NORTH_WEST,Direction.NORTH,Direction.NORTH_EAST,Direction.WEST,Direction.NONE,Direction.EAST,Direction.SOUTH_WEST,Direction.SOUTH,Direction.SOUTH_EAST}; 
        int hqDist = (int)(Math.sqrt(Math.pow(rc.senseEnemyHQLocation().x-rc.senseHQLocation().x,2)+Math.pow(rc.senseEnemyHQLocation().y-rc.senseHQLocation().y,2))); 
        boolean spawned=false; 
        while(true){ 
            try{ 
                  
                if (rc.isActive()) { 
                    // Spawn a soldier 
                    Robot[] allies = rc.senseNearbyGameObjects(Robot.class,100000000,rc.getTeam()); 
                    Robot[] enemies = rc.senseNearbyGameObjects(Robot.class,10000000,rc.getTeam().opponent()); 
                    int amountOfSoldiers=0; 
                    int artillery=0; 
                    //int amountOfArtillery=0; 
  
                    for(Robot r:allies){ 
                        if(r.getClass().equals(RobotType.SOLDIER)){ 
                            amountOfSoldiers++; 
                        } 
                        if(r.getClass().equals(RobotType.ARTILLERY)){ 
                            artillery++; 
                        } 
                    } 
  
                    if(!rc.hasUpgrade(Upgrade.PICKAXE)){ 
                        rc.researchUpgrade(Upgrade.PICKAXE); 
                    } 
                    else if (!rc.hasUpgrade(Upgrade.NUKE)&&artillery>1){ 
                        rc.researchUpgrade(Upgrade.NUKE); 
                    } 
                    else if((amountOfSoldiers<15&&hqDist>100||amountOfSoldiers<25&&hqDist<100)&&allies.length<enemies.length+10){ 
                        for (Direction d:Direction.values()){ 
                            spawned=false; 
                            if (rc.canMove(d)&&(rc.senseMine(rc.getLocation().add(d))==null||rc.senseMine(rc.getLocation().add(d))==rc.getTeam())){ 
                                rc.spawn(d); 
                                spawned=true; 
                            } 
                        } 
                    } 
                    else if(!rc.hasUpgrade(Upgrade.NUKE)){ 
                        rc.researchUpgrade(Upgrade.NUKE); 
                    } 
                    //          Robot[] alliedRobots = rc.senseNearbyGameObjects(Robot.class,100000,rc.getTeam()); 
                      
                } 
                  
                /*if(rc.getEnergon()<300||Clock.getRoundNum()>2000||rc.senseEnemyNukeHalfDone()){//kill enemy if nearing round limit or injured 
                    rallyPt = enemyLoc; 
                }*/
                  
            }catch (Exception e){ 
                System.out.println("HQ Exception"); 
                e.printStackTrace(); 
            } 
            rc.yield(); 
        } 
    } 
} 
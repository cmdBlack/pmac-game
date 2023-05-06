package com.pmac.zero.appWarp;


import java.util.HashMap;
import java.util.Hashtable;

import org.json.JSONObject;

import com.badlogic.gdx.utils.Json;
import com.pmac.zero.Players;
import com.shephertz.app42.gaming.multiplayer.client.Constants;
import com.shephertz.app42.gaming.multiplayer.client.WarpClient;
import com.shephertz.app42.gaming.multiplayer.client.command.WarpResponseResultCode;
import com.shephertz.app42.gaming.multiplayer.client.events.ConnectEvent;
import com.shephertz.app42.gaming.multiplayer.client.events.RoomData;
import com.shephertz.app42.gaming.multiplayer.client.events.RoomEvent;


public class WarpController {

	private static WarpController instance;
	
	private boolean showLog = true;
	
	private final String apiKey = "50ed567f00ef22c35b59189fc4c4ef84a206f2504db606b7d6be98864a69c7d8";
	private final String secretKey = "12827af93c26cd0928f313abe2db7cb790cd3cbff2a0662c3d7065e7890c5e10";
	
	private WarpClient warpClient;
	
	private String localUser;
	private String roomId;
	
	private boolean isConnected = false;
	boolean isUDPEnabled = false;
	
	private WarpListener warpListener ;
	
	private int STATE;
	
	// Game state constants
	public static final int WAITING = 1;
	public static final int STARTED = 2;
	public static final int COMPLETED = 3;
	public static final int FINISHED = 4;
	
	// Game completed constants
	public static final int GAME_WIN = 5;
	public static final int GAME_LOOSE = 6;
	public static final int ENEMY_LEFT = 7;

	public static int playernumbers;
	
	public WarpController() {
		initAppwarp();
		warpClient.addConnectionRequestListener(new ConnectionListener(this));
		warpClient.addChatRequestListener(new ChatListener(this));
		warpClient.addZoneRequestListener(new ZoneListener(this));
		warpClient.addRoomRequestListener(new RoomListener(this));
		warpClient.addNotificationListener(new NotificationListener(this));
	}
	
	public static WarpController getInstance(){
		if(instance == null){
			instance = new WarpController();
		}
		return instance;
	}
	
	public void startApp(String localUser){
		this.localUser = localUser;
		warpClient.connectWithUserName(localUser);
	}
	
	public void setListener(WarpListener listener){
		this.warpListener = listener;
	}
	
	public void stopApp(){
		if(isConnected){
			warpClient.unsubscribeRoom(roomId);
			warpClient.leaveRoom(roomId);
		}
		warpClient.disconnect();
	}
	
	private void initAppwarp(){
		try {
			WarpClient.initialize(apiKey, secretKey);
			warpClient = WarpClient.getInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendGameUpdate(String msg){
		if(isConnected){
			if(isUDPEnabled){
				warpClient.sendUDPUpdatePeers((localUser+"#@"+msg).getBytes());
			}else{
				warpClient.sendUpdatePeers((localUser+"#@"+msg).getBytes());
			}
		}
	}
	
	public void updateResult(int code, String msg){
		if(isConnected){
			STATE = COMPLETED;
			HashMap<String, Object> properties = new HashMap<String, Object>();
			properties.put("result", code);
			warpClient.lockProperties(properties);
		}
	}
	
	public void onConnectDone(boolean status){
		log("onConnectDone: "+status);
		if(status){
			warpClient.initUDP();
			//warpClient.joinRoomInRange(1, 1, false);
			if (Players.playersNumber == 2){
				warpClient.joinRoom("1435804189");
				playernumbers =2;
			}
			else if (Players.playersNumber == 3){
				warpClient.joinRoom("1279845054");
				playernumbers = 3;
			}
			else if (Players.playersNumber == 4){
				warpClient.joinRoom("562400848");
				playernumbers = 4;
			}
			else if (Players.playersNumber == 5){
				warpClient.joinRoom("122323006");
				playernumbers = 5;
			}
			else if (Players.playersNumber == 6){
				warpClient.joinRoom("2039276895");
				playernumbers = 6;
			}
			else if (Players.playersNumber == 7){
				warpClient.joinRoom("1414350766");
				playernumbers = 7;
			}
			else if (Players.playersNumber == 8){
				warpClient.joinRoom("1040059988");
				playernumbers = 8;
			}
			else if (Players.playersNumber == 9){
				warpClient.joinRoom("1164717991");
				playernumbers = 9;
			}
			else if (Players.playersNumber == 10){
				warpClient.joinRoom("568733067");
				playernumbers = 10;
			}
			else if (Players.playersNumber == 50){
				warpClient.joinRoom("1448634890");
				playernumbers = 50;
			}
			//else if (Players.playersNumber == 100){
			//	warpClient.joinRoom("2069152009");
			//}


		}else{
			isConnected = false;
			handleError();
		}
	}

	
	public void onDisconnectDone(boolean status){
		
	}
	
	public void onRoomCreated(String roomId){
		if(roomId!=null){
			warpClient.joinRoom("roomId");
		}else{
			handleError();
		}
	}
	
	public void onJoinRoomDone(RoomEvent event){
		log("onJoinRoomDone: "+event.getResult());
		if(event.getResult()==WarpResponseResultCode.SUCCESS){// success case
			this.roomId = event.getData().getId();
			warpClient.subscribeRoom(roomId);

		}else if(event.getResult()==WarpResponseResultCode.RESOURCE_NOT_FOUND){// no such room found
			HashMap<String, Object> data = new HashMap<String, Object>();
			data.put("result", "");
			warpClient.createRoom("pmac2020LITE", "pmac", 2, data);
			log("success");
		}else{
			warpClient.disconnect();
			handleError();
		}
	}
	
	public void onRoomSubscribed(String roomId){
		log("onSubscribeRoomDone: "+roomId);
		if(roomId!=null){
			isConnected = true;
			warpClient.getLiveRoomInfo(roomId);
		}else{
			warpClient.disconnect();
			handleError();
		}
	}
	
	public void onGetLiveRoomInfo(String[] liveUsers){
		log("onGetLiveRoomInfo: "+liveUsers.length);
		if(liveUsers!=null){
			if(liveUsers.length==2){
				startGame();
			}
			/*if(liveUsers.length==2 && playernumbers == 2){
					startGame();
			}
			else if (liveUsers.length==3 && playernumbers == 3){
				startGame();
			}
			else if (liveUsers.length==4 && playernumbers == 4){
				startGame();
			}
			else if (liveUsers.length==5 && playernumbers == 5){
				startGame();
			}
			else if (liveUsers.length==6 && playernumbers == 6){
				startGame();
			}
			else if (liveUsers.length==7 && playernumbers == 7){
				startGame();
			}
			else if (liveUsers.length==8 && playernumbers == 8){
				startGame();
			}
			else if (liveUsers.length==9 && playernumbers == 9){
				startGame();
			}
			else if (liveUsers.length==10 && playernumbers == 10){
				startGame();
			}
			else if (liveUsers.length==50 && playernumbers == 50){
				startGame();
			}*/
			else{
				waitForOtherUser();
			}
		}else{
			warpClient.disconnect();
			handleError();
		}
	}
	
	public void onUserJoinedRoom(String roomId, String userName){
		/*
		 * if room id is same and username is different then start the game
		 */
		if(localUser.equals(userName)==false){
			startGame();
		}
	}

	public void onSendChatDone(boolean status){
		log("onSendChatDone: "+status);
	}
	
	public void onGameUpdateReceived(String message){
//		log("onMoveUpdateReceived: message"+ message );
		String userName = message.substring(0, message.indexOf("#@"));
		String data = message.substring(message.indexOf("#@")+2, message.length());
		if(!localUser.equals(userName)){
			warpListener.onGameUpdateReceived(data);
		}
	}
	
	public void onResultUpdateReceived(String userName, int code){
		if(localUser.equals(userName)==false){
			STATE = FINISHED;
			warpListener.onGameFinished(code, true);
		}else{
			warpListener.onGameFinished(code, false);
		}
	}
	
	public void onUserLeftRoom(String roomId, String userName){
		log("onUserLeftRoom "+userName+" in room "+roomId);
		if(STATE==STARTED && !localUser.equals(userName)){// Game Started and other user left the room
			warpListener.onGameFinished(ENEMY_LEFT, true);
		}
	}
	
	public int getState(){
		return this.STATE;
	}
	
	private void log(String message){
		if(showLog){
			System.out.println(message);
		}
	}
	
	private void startGame(){
		STATE = STARTED;
		warpListener.onGameStarted("Start the Game");
	}
	
	private void waitForOtherUser(){
		STATE = WAITING;
		warpListener.onWaitingStarted("Waiting for other user");
	}
	
	private void handleError(){
		if(roomId!=null && roomId.length()>0){
			warpClient.deleteRoom(roomId);
		}
		disconnect();
	}
	
	public void handleLeave(){
		if(isConnected){
			warpClient.unsubscribeRoom(roomId);
			warpClient.leaveRoom(roomId);
			if(STATE!=STARTED){
				warpClient.deleteRoom(roomId);
			}
			warpClient.disconnect();
		}
	}
	
	private void disconnect(){
		warpClient.removeConnectionRequestListener(new ConnectionListener(this));
		warpClient.removeChatRequestListener(new ChatListener(this));
		warpClient.removeZoneRequestListener(new ZoneListener(this));
		warpClient.removeRoomRequestListener(new RoomListener(this));
		warpClient.removeNotificationListener(new NotificationListener(this));
		warpClient.disconnect();
	}
}

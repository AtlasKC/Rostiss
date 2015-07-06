package org.rostiss.game.net;

import org.rostiss.game.Rostiss;

import javax.swing.*;
import java.io.IOException;
import java.net.*;

public class Client extends Thread {

	private InetAddress ip;
	private DatagramSocket socket;
	private Rostiss game;

	public Client(Rostiss game, String ip) {
		this.game = game;
		try {
			this.socket = new DatagramSocket();
			this.ip = InetAddress.getByName(ip);
		} catch (SocketException | UnknownHostException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while (true) {
			byte[] data = new byte[1024];
			DatagramPacket packet = new DatagramPacket(data, data.length);
			try {
				socket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
			JOptionPane.showMessageDialog(game, "SERVER >>> " + new String(packet.getData()).trim());
		}
	}
	
	public void sendData(byte[] data) {
		DatagramPacket packet = new DatagramPacket(data, data.length, ip, 8111);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

package user;

import team.Team;

public class User {
	// email and nickname unique
	private String nickname;
	private String password;
	private String name;
	private String surname;
	private int age;
	private String email;
	private String photoPath;
	private Team team;
	private boolean draftingCheck;
	
	public User(String nickname, String name, String surname, int age, String email, String password, String photoPath) {
		this.nickname = nickname;
		this.name = name;
		this.surname = surname;
		this.age = age;
		this.email = email;
		this.photoPath = photoPath;
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
	
	public boolean isDraftingCheck() {
		return draftingCheck;
	}

	public void setDraftingCheck(boolean draftingCheck) {
		this.draftingCheck = draftingCheck;
	}
}

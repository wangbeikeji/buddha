package com.wangbei.pojo;

import com.wangbei.entity.BuddhistMusic;
import com.wangbei.entity.BuddhistMusicCategory;

public class AppHomeTopData {

	/**
	 * 资源id
	 */
	private Integer id;
	/**
	 * 资源类型
	 * 
	 * <ul>
	 * <li>1专辑</li>
	 * <li>2佛音</li>
	 * </ul>
	 */
	private Integer type;
	/**
	 * 播放次数
	 */
	private Integer playTimes = 0;
	/**
	 * 封面链接
	 */
	private String coverLink;
	/**
	 * mp3播放界面封面链接
	 */
	private String coverLink2;
	/**
	 * 专辑名称
	 */
	private String albumName;
	/**
	 * 专辑
	 */
	private String albumAuthor;
	/**
	 * 专辑描述
	 */
	private String albumDescription;
	/**
	 * 歌名
	 */
	private String musicName;
	/**
	 * 作者
	 */
	private String musicAuthor;
	/**
	 * 资源链接
	 */
	private String musicLink;
	/**
	 * 音乐时长
	 */
	private Integer musicDuration;

	public AppHomeTopData(BuddhistMusicCategory category) {
		this.setId(category.getId());
		this.setType(1);
		this.setCoverLink(category.getBannerLink());
		this.setPlayTimes(category.getPlayTimes());
		this.setAlbumAuthor(category.getAuthor());
		this.setAlbumName(category.getName());
		this.setAlbumDescription(category.getTxtDescription());
	}

	public AppHomeTopData(BuddhistMusic music, String coverLink2) {
		this.setId(music.getId());
		this.setType(2);
		this.setCoverLink(music.getBannerLink());
		this.setPlayTimes(music.getPlayTimes());
		this.setMusicName(music.getName());
		this.setMusicAuthor(music.getAuthor());
		this.setMusicLink(music.getLink());
		this.setCoverLink2(coverLink2);
		this.setMusicDuration(music.getDuration());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getCoverLink() {
		return coverLink;
	}

	public void setCoverLink(String coverLink) {
		this.coverLink = coverLink;
	}

	public String getAlbumName() {
		return albumName;
	}

	public void setAlbumName(String albumName) {
		this.albumName = albumName;
	}

	public String getAlbumDescription() {
		return albumDescription;
	}

	public void setAlbumDescription(String albumDescription) {
		this.albumDescription = albumDescription;
	}

	public String getMusicName() {
		return musicName;
	}

	public void setMusicName(String musicName) {
		this.musicName = musicName;
	}

	public String getMusicAuthor() {
		return musicAuthor;
	}

	public void setMusicAuthor(String musicAuthor) {
		this.musicAuthor = musicAuthor;
	}

	public String getMusicLink() {
		return musicLink;
	}

	public void setMusicLink(String musicLink) {
		this.musicLink = musicLink;
	}

	public Integer getPlayTimes() {
		return playTimes;
	}

	public void setPlayTimes(Integer playTimes) {
		this.playTimes = playTimes;
	}

	public String getAlbumAuthor() {
		return albumAuthor;
	}

	public void setAlbumAuthor(String albumAuthor) {
		this.albumAuthor = albumAuthor;
	}

	public Integer getMusicDuration() {
		return musicDuration;
	}

	public void setMusicDuration(Integer musicDuration) {
		this.musicDuration = musicDuration;
	}

	public String getCoverLink2() {
		return coverLink2;
	}

	public void setCoverLink2(String coverLink2) {
		this.coverLink2 = coverLink2;
	}

}

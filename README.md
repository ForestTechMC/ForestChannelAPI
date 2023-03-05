# ForestChannelAPI
![badge](https://img.shields.io/github/v/release/ForestTechMC/ForestChannelAPI)
[![badge](https://jitpack.io/v/ForestTechMC/ForestChannelAPI.svg)](https://jitpack.io/#ForestTechMC/ForestChannelAPI)
![badge](https://img.shields.io/github/downloads/ForestTechMC/ForestChannelAPI/total)
![badge](https://img.shields.io/github/last-commit/ForestTechMC/ForestChannelAPI)
![badge](https://img.shields.io/badge/platform-spigot%20%7C%20bungeecord-lightgrey)
[![badge](https://img.shields.io/discord/896466173166747650?label=discord)](https://discord.gg/2PpdrfxhD4)
[![badge](https://img.shields.io/github/license/ForestTechMC/ForestChannelAPI)](https://github.com/ForestTechMC/ForestChannelAPI/blob/master/LICENSE.txt)

**[JavaDoc 1.0](https://foresttechmc.github.io/ForestChannelAPI/1.1/)**

Have you ever had a problem with channels in plugins? (I had) <br>
That's why I want to introduce you ForestChannelAPI. <br> 
For usage on larger projects, we recommend more using Redis together with our [ForestRedisAPI](https://github.com/ForestTechMC/ForestRedisAPI) instead.

## Table of contents

* [Getting started](#getting-started)
* [Example of events](#example-of-events)
* [Example of manager](#using-color-api)
* [Example in project](#using-color-api)
* [License](#license)

## Getting started

Make sure you reloaded maven or gradle in your project.

### We recommend more using Redis API

The problem in channels at all is <br>
we need some online player to send information to Bungee x Spigot or Spigot x Bungee <br>
Our API for Redis [ForestRedisAPI](https://github.com/ForestTechMC/ForestRedisAPI)

### Add ForestChannelAPI to your project

[![badge](https://jitpack.io/v/ForestTechMC/ForestChannelAPI.svg)](https://jitpack.io/#ForestTechMC/ForestChannelAPI)

You need to add this dependency into your plugin, then look at under the dependencies example

<details>
    <summary>Maven</summary>

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.ForestTechMC</groupId>
        <artifactId>ForestChannelAPI</artifactId>
        <version>VERSION</version>
        <scope>provided</scope>
    </dependency>
</dependencies>
```
</details>

<details>
    <summary>Gradle</summary>

```gradle
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    implementation 'com.github.ForestTechMC:ForestChannelAPI:VERSION'
}
```
</details>

### Example of events

<details>
    <summary>Example of events</summary>

```java
    // Bungee custom event
    @EventHandler
    public void onChannel(ChannelEvent event) {
        ProxiedPlayer player = event.getSender();
        String channel = event.getChannel();
        String message = event.getMessage();

        System.out.println("Our first sender: " + player.getName()); // The person we send from that information
        System.out.println("Our first channel name: " + channel); // Channel name <plugin name>:<channel name>
        System.out.println("Our first message from that channel: " + message); // Message "Omg its working!"
    }

    // Spigot custom event
    @EventHandler
    public void onChannel(ChannelEvent event) {
        Player player = event.getPlayer();
        String channel = event.getChannel();
        String message = event.getMessage();

        System.out.println("Our first sender: " + player.getName()); // The person we send from that information
        System.out.println("Our first channel name: " + channel); // Channel name <plugin name>:<channel name>
        System.out.println("Our first message from that channel: " + message); // Message "Omg its working!"
    }
```
</details>

### Using Channel API

<details>
    <summary>Using API</summary>

```java
        // Import for Bungee
    import cz.foresttech.api.bungee.taker.ChannelAPI;
        // Spigot instance
    private static Bungee instance;

    private ChannelAPI channelAPI;

    @Override 
    public void onEnable() {
        instance = this;

        channelAPI = new ChannelAPI(this);
        channelAPI.register("<channel name>");

    }
    
        // Import for Spigot
    import cz.foresttech.api.spigot.taker.ChannelAPI;

        // Bungee instance
    private static Spigot instance;

    private ChannelAPI channelAPI;

    @Override
    public void onEnable() {
        instance = this;

        channelAPI = new ChannelAPI(this);
        channelAPI.register("<channel name>");
    }
    
        // GLOBAL (Bungee & Spigot)
    
    // Send method
    getChannelAPI().send(player, "<channel name>", "<message>");
    getChannelAPI().registerEvent(this, new SuperEvent());
```
</details>

## License
ForestChannelAPI is licensed under the permissive MIT license. Please see [`LICENSE.txt`](https://github.com/ForestTechMC/ForestChannelAPI/blob/master/LICENSE.txt) for more information.
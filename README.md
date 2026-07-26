<div align="center">

# NoTrace

### You Can't See Me.

![Minecraft](https://img.shields.io/badge/Minecraft-1.21.11%20%7C%2026.1%20%7C%2026.2-5B8731?style=flat-square)
![Loader](https://img.shields.io/badge/Loader-Fabric-DACB3E?style=flat-square)
![License](https://img.shields.io/badge/License-GPLv3-7B68EE?style=flat-square)
![Version](https://img.shields.io/badge/Version-1.0-00BFFF?style=flat-square)
[![QQ Group](https://img.shields.io/badge/QQ%20Group-Join-12B7F5?style=flat-square&logo=tencentqq&logoColor=white)](https://qm.qq.com/q/hSDIROgArC)

</div>

---

## 📌 Overview

**NoTrace** is a Fabric client mod designed to make your connection appear completely vanilla to a server.

It suppresses the client-side traces commonly used by server-side mod-list queries, channel registration checks, brand checks, translation-key probes, and sign-text checks. The server receives a clean, ordinary vanilla-facing response instead of information about the mods installed on your client.

NoTrace is built to cover more identification paths than almost any comparable project:

- No visible mod-list response
- No Fabric channel registration footprint
- Vanilla client brand replacement
- Translation-key probe handling
- Sign text preservation and restoration
- A single switch for enabling every concealment control

The server does not see your mod environment.  
It sees another ordinary vanilla client.

---

## 🛡️ Features

### Effective scope

NoTrace is designed to bypass nearly every form of mod inspection used by nearly any server, including mod-list queries, custom payload channels, client-brand checks, translation-key probes, and sign-text checks.

### One-click client trace hiding

Open the **NoTrace** button in the multiplayer screen and use **一键隐藏客户端痕迹 / Hide Client Traces** to enable every available control at once.

### Vanilla brand response

Outgoing client-brand payloads are replaced with the vanilla brand, keeping the server-facing client identity clean.

### Channel registration control

Fabric custom-channel registration and unregistration traffic is suppressed, preventing servers from obtaining an installed-mod footprint through registered payload channels.

### Translation-key handling

Server-provided translation-key content is handled without exposing client-side localization behavior that can be used for identification.

### Sign text handling

When server content is opened in the sign editor, NoTrace preserves its visible text and restores it for the outgoing update packet.

---

## ⚙️ Settings

You can open the NoTrace settings in either location:

1. **Multiplayer screen** → top-left **NoTrace** button
2. **Mod Menu** → NoTrace → configuration button

The settings UI automatically uses Chinese when the Minecraft game language is Chinese; all other game languages use English.

Configuration is stored in Fabric's config directory:

```text
config/notrace/multiplayer-compatibility.json
```

---

## 📦 Requirements

- Minecraft `1.21.11`, `26.1`, or `26.2`
- Fabric Loader `>= 0.19.3`
- Fabric API
- Java `21` for Minecraft `1.21.11`
- Java `25` for Minecraft `26.x`

Mod Menu is optional. When installed, it provides an additional entry point to the NoTrace settings screen.

---

## 🧱 Multi-version development

One Gradle project builds and launches all three supported versions from the same source tree:

```powershell
# Build every target
.\gradlew.bat buildAll

# Launch Minecraft 1.21.11
.\gradlew.bat :mc-1.21.11:runClient

# Launch Minecraft 26.1
.\gradlew.bat :mc-26.1:runClient

# Launch Minecraft 26.2
.\gradlew.bat :mc-26.2:runClient

# Launch all targets together
.\gradlew.bat runAllClients --parallel
```

---

## 💬 Community

Join the [NoTrace QQ group](https://qm.qq.com/q/hSDIROgArC).

---

## ⚠️ Notice

This mod is provided solely for technical countermeasure discussion and learning.

---

## 📄 License

NoTrace is licensed under the [GNU General Public License v3.0](LICENSE).

---

<div align="center">

**No logs. No alerts. No trace.**

—— NoTrace

</div>

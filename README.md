# Git Parent Folder Coloring Plugin for PhpStorm

[![Build](https://github.com/andreashurst/git-parent-folder-coloring/actions/workflows/build.yml/badge.svg)](https://github.com/andreashurst/git-parent-folder-coloring/actions/workflows/build.yml)
[![Version](https://img.shields.io/github/v/release/andreashurst/git-parent-folder-coloring)](https://github.com/andreashurst/git-parent-folder-coloring/releases)
[![Downloads](https://img.shields.io/github/downloads/andreashurst/git-parent-folder-coloring/total)](https://github.com/andreashurst/git-parent-folder-coloring/releases)

A PhpStorm plugin that colors parent folders in the project tree when they contain Git-modified files, making it easier to see which directories have changes at a glance.

## ✨ Features

- 🎨 **Visual Folder Highlighting**: Parent folders are automatically colored when they contain Git-modified files
- 📍 **Clear Indicators**: Additional "*" symbol for better visibility  
- 🔄 **Real-time Updates**: Automatically updates when Git operations occur (commit, checkout, etc.)
- 📁 **Complete Coverage**: Works with both staged and unstaged files
- ⚡ **Performance Optimized**: Minimal impact on IDE performance
- 🖥️ **Cross-platform**: Works on Windows, macOS, and Linux

## 🚀 Installation

### From GitHub Releases (Recommended)

1. **Download the latest release**:
   - Go to [Releases](https://github.com/andreashurst/git-parent-folder-coloring/releases)
   - Download the `.zip` file from the latest release

2. **Install in PhpStorm**:
   - Open PhpStorm
   - Go to `File → Settings → Plugins` (Windows/Linux) or `PhpStorm → Preferences → Plugins` (macOS)
   - Click the gear icon → `Install Plugin from Disk...`
   - Select the downloaded ZIP file
   - Restart PhpStorm

## 🖼️ Before vs. After

**Before**: Only changed files are colored
```
📁 src/
  📁 components/
    📄 Header.php (red - modified)
  📁 services/
    📄 UserService.php (red - modified)
```

**After**: Parent folders are also colored
```
📁 src/ * (red - contains changes)
  📁 components/ * (red - contains changes)
    📄 Header.php (red - modified)
  📁 services/ * (red - contains changes)
    📄 UserService.php (red - modified)
```

## 🛠️ Development

### Building from Source

```bash
# Clone the repository
git clone https://github.com/andreashurst/git-parent-folder-coloring.git
cd git-parent-folder-coloring

# Build the plugin
./gradlew buildPlugin

# The built plugin will be in build/distributions/
```

### Development Environment

```bash
# Run PhpStorm with the plugin for testing
./gradlew runIde

# Run tests
./gradlew test

# Verify plugin compatibility
./gradlew verifyPlugin
```

## 🔧 Configuration

The plugin works out of the box without additional configuration. Colors are automatically inherited from PhpStorm's Git color scheme.

## 🐛 Troubleshooting

### Plugin not loading
- Ensure PhpStorm version is 2023.1 or newer
- Check that Git plugin is enabled in PhpStorm
- Verify the project is a Git repository

### Folders not being colored
- Ensure VCS integration is enabled: `VCS → Enable Version Control Integration → Git`
- Check that the Git plugin is active in Settings → Plugins
- Try refreshing the project view: `View → Tool Windows → Project → Refresh`

### Performance issues
- The plugin is optimized for performance, but very large repositories might see slower updates
- Consider excluding large directories that don't need monitoring

## 📋 Compatibility

- **PhpStorm**: 2023.1 - 2024.2+
- **Java**: 17 or higher
- **Operating Systems**: Windows, macOS, Linux
- **Git**: Any version supported by PhpStorm

## 🚀 Automated Builds

This project uses GitHub Actions for automated building and testing:

- **Continuous Integration**: Every push and pull request triggers a build
- **Multi-platform Testing**: Tested on Ubuntu, macOS, and Windows
- **Automated Releases**: Tagged releases are automatically built and published
- **Plugin Verification**: Each build is verified against multiple PhpStorm versions

## 🤝 Contributing

Contributions are welcome! Please feel free to submit a Pull Request.

### Development Setup

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/amazing-feature`
3. Make your changes
4. Run tests: `./gradlew test`
5. Commit your changes: `git commit -m 'Add amazing feature'`
6. Push to the branch: `git push origin feature/amazing-feature`
7. Open a Pull Request

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## 🙏 Acknowledgments

- JetBrains for the excellent IntelliJ Platform
- The PhpStorm team for creating an amazing IDE
- The Git4Idea team for Git integration
- All contributors and users who provide feedback

---

**Like this plugin?** ⭐ Give us a star on GitHub!

**Found a bug?** 🐛 [Create an issue](https://github.com/andreashurst/git-parent-folder-coloring/issues/new)

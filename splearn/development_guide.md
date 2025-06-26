# Splearn development guide

## Architecture
- Hexagonal architecture
- Domain model pattern

### Layers
- Domain layer
- Application layer
- Adapter layer

> Actor -> Adapter -> Application -> Domain


## Packages
- domain
- application
    - required
    - provided
- adapter
    - webapi
    - persistence
    - integration
    - security
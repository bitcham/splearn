# Splearn Development Guidelines

## Architecture
- Hexagonal Architecture (Ports and Adapters)
- DDD Patterns


### Layers
- Domain Layer
- Application Layer
- Adapter Layer

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
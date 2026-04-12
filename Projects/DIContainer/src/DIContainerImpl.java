public class DIContainerImpl implements DIContainer {
    @Override
    public <T> void registerSingleton(Class<T> abstraction, Class<? extends T> implementation) {
    }

    @Override
    public <T> void registerTransient(Class<T> abstraction, Class<? extends T> implementation) {
    }

    @Override
    public <T> void registerInstance(Class<T> abstraction, T instance) {
    }

    @Override
    public <T> T resolve(Class<T> abstraction) {
        return null;
    }

    @Override
    public boolean isRegistered(Class<?> abstraction) {
        return false;
    }
}

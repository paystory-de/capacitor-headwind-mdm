import type { PluginListenerHandle } from '@capacitor/core';

export interface OnHeadwindMDMConnectedEvent { }
export type OnHeadwindMDMConnectedListener = (event: OnHeadwindMDMConnectedEvent) => void;

export interface OnHeadwindMDMDisconnectedEvent { }
export type OnHeadwindMDMDisconnectedListener = (event: OnHeadwindMDMDisconnectedEvent) => void;

export interface OnHeadwindMDMConfigChangedEvent { }
export type OnHeadwindMDMConfigChangedListener = (event: OnHeadwindMDMConfigChangedEvent) => void;

export enum ErrorLevel {
  ERROR = 1,
  WARN = 2,
  INFO = 3,
  DEBUG = 4,
  VERBOSE = 5
}

export interface ConnectResult { connected: boolean; }
export interface IsConnectedResult { isConnected: boolean; }
export interface GetServerHostResult { serverHost: string; }
export interface GetSecondaryServerHostResult { secondaryServerHost: string; }
export interface GetServerPathResult { serverPath: string; }
export interface GetServerUrlResult { serverUrl: string; }
export interface GetSecondaryServerUrlResult { secondaryServerUrl: string; }
export interface GetDeviceIdResult { deviceId: string; }
export interface GetCustomResult { custom: string; }
export interface GetResult { value: string; }
export interface SetResult { successful: boolean; }

export interface HeadwindMDMCapacitorPlugin {
  /**
   * Connect to Headwind MDM service and set the event handler. This method returns true on success and false if Headwind MDM is not running.
   *
   * @since 1.0.0
   */
  connect(): Promise<ConnectResult>;

  /**
   * Disconnect from Headwind MDM
   *
   * @since 1.0.0
   */
  disconnect(): Promise<void>;

  /**
   * Check the connection status. You can use the library functions if this method returns true.
   *
   * @since 1.0.0
   */
  isConnected(): Promise<IsConnectedResult>;

  /**
   * Get the host name of Headwind MDM server
   *
   * @since 1.0.0
   */
  getServerHost(): Promise<GetServerHostResult>;

  /**
   * Get the secondary host name of Headwind MDM server
   *
   * @since 1.0.0
   */
  getSecondaryServerHost(): Promise<GetSecondaryServerHostResult>;

  /**
   * Get the path of Headwind MDM web panel
   *
   * @since 1.0.0
   */
  getServerPath(): Promise<GetServerPathResult>;

  /**
   * Get the URL of Headwind MDM server
   *
   * @since 1.0.0
   */
  getServerUrl(): Promise<GetServerUrlResult>;

  /**
   * Get the secondary URL of Headwind MDM server
   *
   * @since 1.0.0
   */
  getSecondaryServerUrl(): Promise<GetSecondaryServerUrlResult>;

  /**
   * Get the device ID (number)
   *
   * @since 1.0.0
   */
  getDeviceId(): Promise<GetDeviceIdResult>;

  /**
   * Get the custom variable defined on the server (number can be from 1 to 3)
   *
   * @since 1.0.0
   */
  getCustom(options: { number: number; }): Promise<GetCustomResult>;

  /**
   * Sends a log message to the server
   *
   * @since 1.0.0
   */
  log(options: { level: ErrorLevel; tag: string; message: string; }): Promise<void>;

  /**
   * These methods are similar to android.util.Log. They combine writing to Android internal log (logcat) and sending the log message to the server.
   *
   * @since 1.0.0
   */
  v(options: { tag: string; message: string; }): Promise<void>;

  /**
   * These methods are similar to android.util.Log. They combine writing to Android internal log (logcat) and sending the log message to the server.
   *
   * @since 1.0.0
   */
  d(options: { tag: string; message: string; }): Promise<void>;

  /**
   * These methods are similar to android.util.Log. They combine writing to Android internal log (logcat) and sending the log message to the server.
   *
   * @since 1.0.0
   */
  i(options: { tag: string; message: string; }): Promise<void>;

  /**
   * These methods are similar to android.util.Log. They combine writing to Android internal log (logcat) and sending the log message to the server.
   *
   * @since 1.0.0
   */
  w(options: { tag: string; message: string; }): Promise<void>;

  /**
   * These methods are similar to android.util.Log. They combine writing to Android internal log (logcat) and sending the log message to the server.
   *
   * @since 1.0.0
   */
  e(options: { tag: string; message: string; }): Promise<void>;

  /**
   * Gets an attribute defined in “Application settings” tab of the configuration.
   *
   * @since 1.0.0
   */
  get(options: { attr: string; defValue?: string; }): Promise<GetResult>;

  /**
   * Sets an attribute (not yet implemented)
   *
   * @since 1.0.0
   */
  set(options: { attr: string; value: string; }): Promise<SetResult>;

  /**
   * Forces uploading the attributes to the server (not yet implemented)
   *
   * @since 1.0.0
   */
  apply(): Promise<void>;

  /**
   * Called when the connection is established. In this method, you can retrieve your app settings from the server.
   *
   * @since 1.0.0
   */
  addListener(
      eventName: 'onHeadwindMDMConnected',
      listenerFunc: OnHeadwindMDMConnectedListener
  ): Promise<PluginListenerHandle> & PluginListenerHandle;

  /**
   * Called when the connection is interrupted. This method doesn’t require any actions and it is usually followed by onHeadwindMDMConnected() within some seconds.
   *
   * @since 1.0.0
   */
  addListener(
      eventName: 'onHeadwindMDMDisconnected',
      listenerFunc: OnHeadwindMDMDisconnectedListener
  ): Promise<PluginListenerHandle> & PluginListenerHandle;

  /**
   * Called when the application settings were changed on the server. In this method, you need to refresh your application settings.
   *
   * @since 1.0.0
   */
  addListener(
      eventName: 'onHeadwindMDMConfigChanged',
      listenerFunc: OnHeadwindMDMConfigChangedListener
  ): Promise<PluginListenerHandle> & PluginListenerHandle;
}
